package cn.edu.hdu.clan.controller;


import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.entity.sys.SysUser;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.util.PropertiesUtils;
import tk.mybatis.mapper.util.StringUtil;


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


    @RequestMapping("/")
     public String login_tologin() {
         return "login";}
     /*public String index() {
        return "index";
    }*/

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
    @RequestMapping("/ordersheet")
    public String ordersheet() {
        return "ordersheet";
    }
    @RequestMapping("/advertise")
    public String advertise() {
        return "advertise";
    }
    @RequestMapping("/productionsheet")
    public String productionsheet() {
        return "productionsheet";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestBody Map<String, String> params) {
        Subject subject = SecurityUtils.getSubject();
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UsernamePasswordToken(params.get("username"), params.get("password")));
            Session session = Jurisdiction.getSession();

            SysUser sysUser  = userService.findByUsername(params.get("username"));
            SysTeam  sysTeam = sysTeamService.getById(sysUser.getTeamId());

            session.setAttribute(Const.SESSION_USER,sysUser);
            session.setAttribute(Const.SESSION_USERID,sysUser.getId());
            session.setAttribute(Const.SESSION_USERTEAM,sysUser.getTeamId());
            session.setAttribute(Const.SESSION_USERPERIOD,sysTeam.getState().toString());  //当前的会计期间




            return success("登陆成功");
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
            return success("登陆失败");
        }

    }



    @ResponseBody
    @RequestMapping("register")
    public String register(@RequestBody Map<String, String> params) {
        SysUser user = new SysUser();
        user.setUsername(params.get("username"));
        user.setPassword(params.get("password"));
        userService.addUser(user);
        return success("注册成功");
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
        String userTeam = params.get("userTeam");
        int period = Integer.parseInt(params.get("period"));

        //初始化到第一个会计期间。
        sysTeamService.reloadData(userTeam,period);

        return success("初始化成功");
    }




    @RequestMapping("closing")
    public String closing(){
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());


        //期末损益结转
        accountingVoucherService.transferProfitAndLoss(userTeam,period);

        //清除本期的科目余额表
        accountBalanceService.deleteByPeriod(userTeam,period);
        //从会计凭证表汇总本期的发生额到科目余额表
        accountBalanceService.sumFromVoucher(userTeam,period);
        //获取当前会计期间的科目余额表
        List<AccountBalance> accountBalances = accountBalanceService.getByTeamcountAndPeriod(userTeam,period);
        //根据科目余额表，生成本期的资产负债表。
        balancesheetService.createBalanceSheet(accountBalances,userTeam,period);
        //根据科目余额表，生成本期的利润表。
        incomesheetService.createIncomeSheet(accountBalances,userTeam,period);





        int nextPeriod = Integer.parseInt(Jurisdiction.getUserTeamintPeriod())+1;  //注意：结账的时候，会计期间要跳转到下一期。+1

        //原材料订单到期，会计账务处理：现金减少
        materialOrderService.payment(userTeam,nextPeriod);

        //应收账款到期，会计账务处理：现金增加
        salepaymentService.receivePayment(userTeam,nextPeriod);


        //复制厂房信息到下一会计期间。
        factoryService.copyDataToNextPeriod(userTeam,period,nextPeriod);


        //复制生产线信息到下一会计期间。
        productLineService.copyDataToNextPeriod(userTeam,period,nextPeriod);





        return success();
    }



    @RequestMapping("loginlab")
    public String loginlab() {
        return "loginlab";

    }

    @ResponseBody
    @RequestMapping("/loginlabTo")
    public String loginlabTo(@RequestBody Map<String, String> params) {
        String userName = params.get("username");
        Subject subject = SecurityUtils.getSubject();
        try{
            // 调用安全认证框架的登录方法
            subject.login(new UsernamePasswordToken("yang", "1")); //用默认的用户先登录
            Session session = Jurisdiction.getSession();

            SysUser sysUser  = userService.findByUsername("yang");
            SysTeam  sysTeam = sysTeamService.getById(sysUser.getTeamId());

            session.setAttribute(Const.SESSION_USER,sysUser);
            session.setAttribute(Const.SESSION_USERID,sysUser.getId());
            session.setAttribute(Const.SESSION_USERTEAM,sysUser.getTeamId());
            session.setAttribute(Const.SESSION_USERPERIOD,sysTeam.getState().toString());  //当前的会计期间



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
                userNew.setTeamId(userTeam.getId());
                userNew.setPassword("123456");
                userNew.setRegistrationTime(new Date());
                userNew.setTelephome(800);
                sysUserService.add(userNew);

                SysUser sysUser3  = userService.findByUsername(userName);
                session.setAttribute(Const.SESSION_USER,sysUser3);
                session.setAttribute(Const.SESSION_USERID,sysUser3.getId());
                session.setAttribute(Const.SESSION_USERTEAM,sysUser3.getTeamId());
                session.setAttribute(Const.SESSION_USERPERIOD,userTeam.getState().toString());  //当前的会计期间


            }else{
                SysTeam  sysTeam2 = sysTeamService.getById(sysUser2.getTeamId());
                session.setAttribute(Const.SESSION_USER,sysUser2);
                session.setAttribute(Const.SESSION_USERID,sysUser2.getId());
                session.setAttribute(Const.SESSION_USERTEAM,sysUser2.getTeamId());
                session.setAttribute(Const.SESSION_USERPERIOD,sysTeam2.getState().toString());  //当前的会计期间
            }




            return success("登陆成功");
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
            return success("登陆失败");
        }

    }
}
