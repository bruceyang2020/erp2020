package cn.edu.hdu.clan.controller;


import cn.edu.hdu.clan.entity.sys.*;
import cn.edu.hdu.clan.service.sys.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.session.Session;
import cn.edu.hdu.clan.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.util.PropertiesUtils;
import tk.mybatis.mapper.util.StringUtil;
import java.text.SimpleDateFormat;


/**
 * @author yangying
 * @function
 * @date 20120/1/31.
 */
@Controller
public class IndexController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private UserService userService;
    @Resource
    private SysTeamService sysTeamService;
    @Resource
    private MaterialOrderService materialOrderService;
    @Resource
    private SalepaymentService salepaymentService;

    @Resource
    private AccountBalanceService accountBalanceService;

    @Resource
    private BalancesheetService balancesheetService;

    @Resource
    private  IncomesheetService incomesheetService;
    @Resource
    private  FactoryService  factoryService;
    @Resource
    private ProductLineService productLineService;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private LongTermLoansService longTermLoansService;

    @Resource
    private ShortTermLoanService shortTermLoanService;

    @Resource
    private UsuryService usuryService;

    @Resource
    private InvService invService;

    @Resource
    private ResearchFeeService researchFeeService;

    @Resource
    private MarketFeeService marketFeeService;

    @Resource
    private IsoFeeService isoFeeService;
    @Resource
    private AdvertiseService advertiseService ;

    @Resource
    private OrderManagementService orderManagementService  ;





     //Y 把登录入口改了一个路由，这样就不跟教育部平台接口冲突了
    @RequestMapping("/toLogin")
     public String tologin() {
         return "login";}

    @RequestMapping("/")
    public String login_tologin() {
        return "loginlab";}


    @RequestMapping("/index")
     public String index() {
        return "index";
    }


    @RequestMapping("/balancesheet")
    public String balancesheet() {
        return "balancesheet";
    }
    @RequestMapping("/incomesheet")
    public String incomesheet() {
        return "incomesheet";
    }
    @RequestMapping("/marketingPre")
    public String marketingPre() {
        return "marketingPre";
    }
    @RequestMapping("/marketingPrePriceTotal")
    public String marketingPrePriceTotal() {
        return "marketingPrePriceTotal";
    }

    @RequestMapping("/marketingPrePrice")
    public String marketingPrePrice() {
        return "marketingPrePrice";
    }

    @RequestMapping("/ordersheet")
    public String ordersheet() {
        return "ordersheet";
    }

    @RequestMapping("/ordermanagement")
    public String ordermanagement() {
        return "ordermanagement";
    }

    @RequestMapping("/advertise")
    public String advertise() {
        return "advertise";
    }


    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestBody Map<String, String> params) {
        Subject subject = SecurityUtils.getSubject();

        String userName = params.get("username");
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UsernamePasswordToken(params.get("username"), params.get("password")));
            Session session = Jurisdiction.getSession();

            SysUser sysUser  = userService.findByUsername(userName);
            SysTeam  sysTeam = sysTeamService.getById(sysUser.getTeamId());



            session.setAttribute(Const.SESSION_USER,sysUser);
            session.setAttribute(Const.SESSION_USERID,sysUser.getId());
            session.setAttribute(Const.SESSION_USERTEAM,sysUser.getTeamId());
            session.setAttribute(Const.SESSION_USERPERIOD,sysTeam.getState().toString());  //当前的会计期间
            session.setAttribute(Const.SESSION_EID,"888"); //将教育部平台传过来的实验ID保持，
            session.setAttribute(Const.SESSION_ILABNAME,"guest"); //将教育部平台传过来的实验ID保持，



            return success("登陆成功");
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
            return success("登陆失败");
        }

    }



    @ResponseBody
    @RequestMapping("register")
    public String register(@RequestBody Map<String, String> params) {

        String userName = params.get("username");

        SysUser sysUserExist  = userService.findByUsername(userName);

        if(sysUserExist == null) {
            SysUser user = new SysUser();
            user.setUsername(params.get("username"));
            user.setPassword(params.get("password"));
            user.setTeamId(params.get("username"));
            userService.addUser(user);
            SysUser sysUser  = userService.findByUsername(userName);
            SysTeam  sysTeam = sysTeamService.getById(sysUser.getTeamId());

            if(sysTeam == null)
            {
                //先建立一个群组保存。根据ID
                SysTeam userTeam = new SysTeam();
                userTeam.setGroupId("1000");
                userTeam.setName(userName);
                userTeam.setState(1);//设置当前会计期间为1
                sysTeamService.add(userTeam);
            }


            return success("注册成功");


        }else
        {
            return success("注册失败");
        }



    }

    @ResponseBody
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return success("退出成功");
    }

     //初始化公司的数据
    @ResponseBody
    @RequestMapping(value = "reloaddata",produces = "application/json;charset=utf-8")
    public String reloaddata(@RequestBody Map<String, String> params) {
        //Y 没有用前端传过来的参数，而是直接用了当前session里的参数。
        String userTeam = Jurisdiction.getUserTeam();

        //Y 需要将当前session中保存的会计期间重置为1。
        Session session = Jurisdiction.getSession();
        session.setAttribute(Const.SESSION_USERPERIOD,1);  //当前的会计期间
        //初始化到第一个会计期间。
        sysTeamService.reloadData(userTeam,1);

        return success("初始化成功");
    }



    @ResponseBody
    @RequestMapping(value = "closing",produces = "application/json;charset=utf-8")
    public String closing(@RequestBody Map<String, String> params){
        //Y 没有用前端传过来的参数，而是直接用了当前session里的参数。
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss SSS");//定义时间变量，并设置显示格式为： 时-分-秒-毫秒

        /*------------------------------------------------期间结转----------------------------------------------------------------*/

        System.out.print("核算过程1扣减本期终合办公费："+sdf.format(new Date()));
        //H 扣减行政管理费用会计凭证：这里默认为1
        accountingVoucherService.voucherMaker(userTeam,period,new BigDecimal("1"),"GLFY","管理费用");

        System.out.print("核算过程2折旧核算开始："+sdf.format(new Date()));
        //H 折旧费用的会计凭证
        productLineService.voucherMakerOfDep(userTeam,period);

        System.out.print("核算过程3维修核算开始："+sdf.format(new Date()));
       //H 维修费用的会计凭证
        productLineService.voucherMakerOfMT(userTeam,period);

        System.out.print("核算过程4高利贷计息开始："+sdf.format(new Date()));
        //H 高利贷贷款利息的会计凭证,每期期末计
        usuryService.voucherMakerOfInterest(userTeam,period);

        System.out.print("核算过程5缴上年的所得税开始："+sdf.format(new Date()));
       //H 缴纳上年度所得税
        accountBalanceService.makeVoucherOfTax(userTeam,period);

        System.out.print("核算过程6转上年度净利开始："+sdf.format(new Date()));
        //H 转上年度年度净利
        accountBalanceService.makeVoucherOfNI(userTeam,period);


        System.out.print("期末损益："+sdf.format(new Date()));
        //期末损益结转
        accountingVoucherService.transferToProfitAndLoss(userTeam,period);

        System.out.print("更新本期科目余额表："+sdf.format(new Date()));
        //从会计凭证表汇总本期的发生额到科目余额表
        accountBalanceService.sumFromVoucher(userTeam,period);


        System.out.print("生成BS、IS报表："+sdf.format(new Date()));
        //获取当前会计期间的科目余额表
        List<AccountBalance> accountBalances = accountBalanceService.getByTeamcountAndPeriod(userTeam,period);
        //根据科目余额表，生成本期的利润表。
        incomesheetService.createIncomeSheet(accountBalances,userTeam,period);
        //根据科目余额表，生成本期的资产负债表。
        balancesheetService.createBalanceSheet(accountBalances,userTeam,period);



       /*------------------------------------------------下一期发生的动态-----------------------------------------------------------------*/

        System.out.print("跳转会计期间："+sdf.format(new Date()));
        int nextPeriod = Integer.parseInt(Jurisdiction.getUserTeamintPeriod())+1;  //注意：结账的时候，会计期间要跳转到下一期。+1
        //Y 将当前用户组的state值修改成下一个会计期间。
        sysTeamService.nextPeriod(userTeam,nextPeriod);

        //Y 需要将当前session中保存的会计期间也跳转到下一个会计期间。
        Session session = Jurisdiction.getSession();
        session.setAttribute(Const.SESSION_USERPERIOD,nextPeriod);  //当前的会计期间

        /*------------------------------------------------复制到下一会计期间----------------------------------------------------------------*/

        System.out.print("核算过程7：复制"+sdf.format(new Date()));
        //H 长期贷款复制到下一会计期间
        longTermLoansService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程8：复制"+sdf.format(new Date()));
        //H 短期贷款复制到下一会计期间
        shortTermLoanService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程9：复制"+sdf.format(new Date()));
        //H 应收账款复制到下一会计期间
        salepaymentService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程10：复制"+sdf.format(new Date()));
        //H 订单信息复制到下一会计期间
        materialOrderService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程11：复制"+sdf.format(new Date()));
        //H 存货信息复制到下一会计期间
        invService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程12：复制"+sdf.format(new Date()));
        //复制厂房信息到下一会计期间。
        factoryService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程13：复制"+sdf.format(new Date()));
        //H 复制生产线信息到下一会计期间。包含了产品入库
        productLineService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程14：复制"+sdf.format(new Date()));
        //复制市场开拓信息到下一期
        marketFeeService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程15：复制"+sdf.format(new Date()));
        //复制产品研发信息到下一期
        researchFeeService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        System.out.print("核算过程16：复制"+sdf.format(new Date()));
        //复制ISO认证到信息到下一期
        isoFeeService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        //复制高利贷到下一期
        usuryService.copyDataToNextPeriod(userTeam,period,nextPeriod);

        /*------------------------------------------------下一会计期间开始----------------------------------------------------------------*/
        System.out.print("核算过程17：短贷还本还息"+sdf.format(new Date()));
        //短期贷款回收期减少，还息还本的会计凭证，还息记入下一年度财务费用
        shortTermLoanService.voucherMakerOfInterestAndRepayment(userTeam,nextPeriod);

        System.out.print("核算过程18：长贷还本还息"+sdf.format(new Date()));
        //长期贷款回收期减少，还本，第一期借第四期结转时候还贷记入下一年度财务费用
        longTermLoansService.voucherMakerOfInterestAndRepayment(userTeam,nextPeriod);

        System.out.print("核算过程19收到应收账款："+sdf.format(new Date()));
        //应收账款到期，会计账务处理：现金增加
        salepaymentService.receivePayment(userTeam,nextPeriod);

        System.out.print("核算过程20采购订单到期支付："+sdf.format(new Date()));
        //原材料订单到期，会计账务处理：现金减少
        materialOrderService.payment(userTeam,nextPeriod);

        System.out.print("核算过程21材料入库："+sdf.format(new Date()));
        //H 原材料订单到期，材料入库
        invService.goToPeriod(userTeam,nextPeriod);



        return success("结转成功");
    }


    //回退的功能：基本原理是把本期数据删除，同时把上一期的数据删除，将上两期的期末数据更新到上一期，实现上一期的期初数据状态
    @ResponseBody
    @RequestMapping(value = "priordata",produces = "application/json;charset=utf-8")
    public String priordata(@RequestBody Map<String, String> params){
        //Y 没有用前端传过来的参数，而是直接用了当前session里的参数。
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss SSS");//定义时间变量，并设置显示格式为： 时-分-秒-毫秒

        /*------------------------------------------------将本期数据清场-----------------------------------------------------------------*/
        accountBalanceService.deleteByTeamCountAndPeriod(userTeam,period);
        accountingVoucherService.deleteByTeamCountAndPeriod(userTeam,period);
        advertiseService.deleteByTeamCountAndPeriod(userTeam,period);
        balancesheetService.deleteByTeamCountAndPeriod(userTeam,period);
        factoryService.deleteByTeamCountAndPeriod(userTeam,period);
        incomesheetService.deleteByTeamCountAndPeriod(userTeam,period);
        invService.deleteByTeamCountAndPeriod(userTeam,period);
        isoFeeService.deleteByTeamCountAndPeriod(userTeam,period);
        longTermLoansService.deleteByTeamCountAndPeriod(userTeam,period);
        marketFeeService.deleteByTeamCountAndPeriod(userTeam,period);
        materialOrderService.deleteByTeamCountAndPeriod(userTeam,period);
        orderManagementService.deleteByTeamCountAndPeriod(userTeam,period);
        productLineService.deleteByTeamCountAndPeriod(userTeam,period);
        researchFeeService.deleteByTeamCountAndPeriod(userTeam,period);
        salepaymentService.deleteByTeamCountAndPeriod(userTeam,period);
        shortTermLoanService.deleteByTeamCountAndPeriod(userTeam,period);
        usuryService.deleteByTeamCountAndPeriod(userTeam,period);


        /*------------------------------------------------跳转到上一期并且数据清场-----------------------------------------------------------------*/
        int priorPeriod = period-1;  //注意：结账的时候，会计期间要跳转到前一期。-1
        //Y 将当前用户组的state值修改成前一个会计期间。
        sysTeamService.priorPeriod(userTeam,priorPeriod);
        //Y 需要将当前session中保存的会计期间也跳转到前一个会计期间。
        Session session = Jurisdiction.getSession();
        session.setAttribute(Const.SESSION_USERPERIOD,priorPeriod);  //设置session中的当前的会计期间

        accountBalanceService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        accountingVoucherService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        advertiseService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        balancesheetService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        factoryService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        incomesheetService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        invService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        isoFeeService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        longTermLoansService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        marketFeeService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        materialOrderService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        orderManagementService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        productLineService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        researchFeeService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        salepaymentService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        shortTermLoanService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);
        usuryService.deleteByTeamCountAndPeriod(userTeam,priorPeriod);


        /*------------------------------------------------上一期的跳转之后，期初操作同closing-----------------------------------------------------------------*/

        int priorPeriod2 = period-2;  //会计期间-回退期间的再前一期。-2

        System.out.print("核算过程1：复制"+sdf.format(new Date()));
        //H 长期贷款复制到下一会计期间
        longTermLoansService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);
        System.out.print("核算过程2：复制"+sdf.format(new Date()));
        //H 短期贷款复制到下一会计期间
        shortTermLoanService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);
        System.out.print("核算过程3：复制"+sdf.format(new Date()));
        //H 应收账款复制到下一会计期间
        salepaymentService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);
        System.out.print("核算过程4：复制"+sdf.format(new Date()));
        //H 订单信息复制到下一会计期间
        materialOrderService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程5：复制"+sdf.format(new Date()));
        //H 存货信息复制到下一会计期间
        invService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程6：复制"+sdf.format(new Date()));
        //复制厂房信息到下一会计期间。
        factoryService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程7：复制"+sdf.format(new Date()));
        //复制生产线信息到下一会计期间。包含了产品入库
        productLineService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程8：复制"+sdf.format(new Date()));
        //复制市场开拓信息到下一期
        marketFeeService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程9：复制"+sdf.format(new Date()));
        //复制产品研发信息到下一期
        researchFeeService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        System.out.print("核算过程10：复制"+sdf.format(new Date()));
        //复制ISO认证到信息到下一期
        isoFeeService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        usuryService.copyDataToNextPeriod(userTeam,priorPeriod2,priorPeriod);

        /*------------------------------------------------下一会计期间开始----------------------------------------------------------------*/
        System.out.print("核算过程11：短贷还本还息"+sdf.format(new Date()));
        //短期贷款回收期减少，还息还本的会计凭证，还息记入下一年度财务费用
        shortTermLoanService.voucherMakerOfInterestAndRepayment(userTeam,priorPeriod);
        System.out.print("核算过程12：长贷还本还息"+sdf.format(new Date()));
        //长期贷款回收期减少，还本，第一期借第四期结转时候还贷记入下一年度财务费用
        longTermLoansService.voucherMakerOfInterestAndRepayment(userTeam,priorPeriod);

        System.out.print("核算过程13收到应收账款："+sdf.format(new Date()));
        //应收账款到期，会计账务处理：现金增加
        salepaymentService.receivePayment(userTeam,priorPeriod);

        System.out.print("核算过程14采购订单到期支付："+sdf.format(new Date()));
        //原材料订单到期，会计账务处理：现金减少
        materialOrderService.payment(userTeam,priorPeriod);

        System.out.print("核算过程15材料入库："+sdf.format(new Date()));
        //H 原材料订单到期，材料入库
        invService.goToPeriod(userTeam,priorPeriod);




        return success("结转成功");
    }


    @ResponseBody
    @RequestMapping("/loginlabTo")
    public String loginlabTo(@RequestBody Map<String, String> params) {
        String userName = params.get("username");
        String eId = params.get("eid");
        String ilabName = params.get("ilabname");
        Subject subject = SecurityUtils.getSubject();

        try{

            SysUser sysUser2  = userService.findByUsername(userName);

            //当教育部平台传过来的USERNAME 也就是ID在系统中不存在。
            if (sysUser2 == null) {
                  //先建立一个群组保存。根据ID
                SysTeam userTeam = new SysTeam();
                userTeam.setGroupId("1000");
                userTeam.setName(userName);
                userTeam.setState(1);//设置当前会计期间为1
                sysTeamService.add(userTeam);
                userTeam = sysTeamService.getByName(userName);

                SysUser userNew = new SysUser();
                userNew.setUsername(userName);
                userNew.setTeamId(userName);
                userNew.setIlabName(ilabName);
                userNew.setPassword("123456");
                userNew.setRegistrationTime(new Date());
                userNew.setTelephome(800);
                sysUserService.add(userNew);

                SysUser sysUser3  = userService.findByUsername(userName);

                subject.login(new UsernamePasswordToken(userName, "123456"));
                Session session = Jurisdiction.getSession();
                session.setAttribute(Const.SESSION_USER,sysUser3);
                session.setAttribute(Const.SESSION_USERID,sysUser3.getId());
                session.setAttribute(Const.SESSION_USERTEAM,sysUser3.getTeamId());
                session.setAttribute(Const.SESSION_ILABNAME,ilabName);
                session.setAttribute(Const.SESSION_USERPERIOD,userTeam.getState().toString());  //当前的会计期间
                session.setAttribute(Const.SESSION_EID,eId); //将教育部平台传过来的实验ID保持，用于提交成绩


                //初始化到第一个会计期间。
                sysTeamService.reloadData(userName,1);
                //Y 需要将当前session中保存的会计期间重置为1。
                session.setAttribute(Const.SESSION_USERPERIOD,1);  //当前的会计期间

            }else{
                SysTeam  sysTeam2 = sysTeamService.getById(userName);

                subject.login(new UsernamePasswordToken(sysUser2.getUsername(), sysUser2.getPassword()));
                Session session = Jurisdiction.getSession();
                session.setAttribute(Const.SESSION_USER,sysUser2);
                session.setAttribute(Const.SESSION_USERID,sysUser2.getId());
                session.setAttribute(Const.SESSION_USERTEAM,sysUser2.getTeamId());
                session.setAttribute(Const.SESSION_ILABNAME,ilabName);
                session.setAttribute(Const.SESSION_USERPERIOD,sysTeam2.getState().toString());  //当前的会计期间
                session.setAttribute(Const.SESSION_EID,eId); //将教育部平台传过来的实验ID保持，用于提交成绩
            }




            return success("登陆成功");
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
            return success("登陆失败");
        }

    }
}
