package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.PageData;
import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountBalanceMapper;
import cn.edu.hdu.clan.mapper.sys.AccountingVoucherMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountingVoucherServiceImpl implements AccountingVoucherService {

    @Autowired
    private AccountingVoucherMapper AccountingVoucherMapper;

    @Resource
    private  AccountBalanceService accountBalanceService;

    @Resource
    private  AccountingVoucherService accountingVoucherService;


    @Transactional
    @Override
    public void add(AccountingVoucher AccountingVoucher) {
        BaseBeanHelper.insert(AccountingVoucher);
        AccountingVoucherMapper.insert(AccountingVoucher);
    }

    @Override
    public void delete(String id) {
    AccountingVoucherMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);

        AccountingVoucherMapper.deleteByExample(example);

    }

    @Override
    public void update(AccountingVoucher AccountingVoucher) {
        BaseBeanHelper.edit(AccountingVoucher);
        Example example = new Example(AccountingVoucher.class);
        example.createCriteria().andEqualTo("id", AccountingVoucher.getId());
        AccountingVoucherMapper.updateByExampleSelective(AccountingVoucher, example);
    }



    @Override
    // H  所得税和结转期末损益会计凭证记录  Y 性能优化，不使用sumMonye。将查询加载到内存后，再计算，并将查询条件数据范围缩小
    public void transferToProfitAndLoss(String teamCount, int period){

        BigDecimal netMoney = BigDecimal.valueOf(0);
        BigDecimal taxMoney = BigDecimal.valueOf(0);
        BigDecimal revenue=BigDecimal.valueOf(0);
        BigDecimal cost=BigDecimal.valueOf(0);
        BigDecimal variableCost=BigDecimal.valueOf(0);
        BigDecimal dep=BigDecimal.valueOf(0);
        BigDecimal otherCost=BigDecimal.valueOf(0);
        BigDecimal interest=BigDecimal.valueOf(0);


       // 一般不可能出现例如销售收入贷方为负值，所以没有考虑特殊情况


        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", teamCount);
        criteria.andEqualTo("period", period);
        List<String> values=new ArrayList<String>();
        values.add("销售收入");
        values.add("直接成本");
        values.add("综合费用");
        values.add("折旧费用");
        values.add("财务支出");
        values.add("其他支出");
        criteria.andIn("acode",values);
        List<AccountingVoucher> oldRow = AccountingVoucherMapper.selectByExample( example);
        for(int i=0;i<oldRow.size();i++)
        {

            if( null !=oldRow.get(i).getMoneyD() && "销售收入".equals(oldRow.get(i).getAcode())) {  revenue = revenue.subtract(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "销售收入".equals(oldRow.get(i).getAcode())) {  revenue = revenue.add(oldRow.get(i).getMoneyC()); }

            if( null !=oldRow.get(i).getMoneyD() && "直接成本".equals(oldRow.get(i).getAcode())) {  cost = cost.add(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "直接成本".equals(oldRow.get(i).getAcode())) {  cost = cost.subtract(oldRow.get(i).getMoneyC()); }

            if( null !=oldRow.get(i).getMoneyD() && "综合费用".equals(oldRow.get(i).getAcode())) {  variableCost = variableCost.add(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "综合费用".equals(oldRow.get(i).getAcode())) {  variableCost = variableCost.subtract(oldRow.get(i).getMoneyC()); }

            if( null !=oldRow.get(i).getMoneyD() && "折旧费用".equals(oldRow.get(i).getAcode())) {  dep = dep.add(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "折旧费用".equals(oldRow.get(i).getAcode())) {  dep = dep.subtract(oldRow.get(i).getMoneyC()); }

            if( null !=oldRow.get(i).getMoneyD() && "财务支出".equals(oldRow.get(i).getAcode())) {  otherCost = otherCost.add(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "财务支出".equals(oldRow.get(i).getAcode())) {  otherCost = otherCost.subtract(oldRow.get(i).getMoneyC()); }

            if( null !=oldRow.get(i).getMoneyD() && "其他支出".equals(oldRow.get(i).getAcode())) {  interest = interest.add(oldRow.get(i).getMoneyD()); }
            if( null !=oldRow.get(i).getMoneyC() && "其他支出".equals(oldRow.get(i).getAcode())) {  interest = interest.subtract(oldRow.get(i).getMoneyC()); }





        }

        netMoney=netMoney.add(revenue.subtract(cost.add(variableCost.add(dep.add(otherCost.add(interest))))));



        if (netMoney.compareTo(BigDecimal.valueOf(0)) == 1) //当毛利大于零的时候
        {
            taxMoney = netMoney.multiply(BigDecimal.valueOf(0.25)).setScale(0, BigDecimal.ROUND_DOWN);  //所得税0.25,向下取整。这个BIGDECIMAL的语法真的是够烦的！
            netMoney = netMoney.subtract(taxMoney);
        }
        else{taxMoney=BigDecimal.valueOf(0);}
        accountingVoucherService.voucherMaker(teamCount,period,taxMoney,"SDS","所得税费用");
        System.out.println(revenue);
        System.out.println(variableCost);
        System.out.println(cost);
        //H 期间损益结转
        accountingVoucherService.voucherMaker(teamCount,period,revenue,"XSXRE","期间损益结转");//H 销售费用结转
        accountingVoucherService.voucherMaker(teamCount,period,cost,"ZJCBE","期间损益结转");//H 直接成本结转
        accountingVoucherService.voucherMaker(teamCount,period,variableCost,"ZHFYE","期间损益结转");//H 综合费用结转
        accountingVoucherService.voucherMaker(teamCount,period,dep,"ZJFYE","期间损益结转");//H 折旧费用结转
        accountingVoucherService.voucherMaker(teamCount,period,interest,"CWFYE","期间损益结转");//H 财务费用结转
        accountingVoucherService.voucherMaker(teamCount,period,otherCost,"QTFYE","期间损益结转");//H 其他费用结转
        accountingVoucherService.voucherMaker(teamCount,period,taxMoney,"SDSFYE","期间损益结转");//H 所得税费用结转
        accountingVoucherService.voucherMaker(teamCount,period,netMoney,"BNLRE","期间损益结转");//H 年度净利结转 负目前不做调整，放在资产负债表和利润表都是负值.



    }




    @Override
    // 本期借贷合计数,Y 这个算法调用过于频繁，以至于影响数据库交互性能，后面尽量不用了。
    public BigDecimal sumMoney(String userTeam ,int period,String acode,String aType) {
        BigDecimal myMoney = BigDecimal.valueOf(0);
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("acode", acode);
        criteria.andEqualTo("period", period);
        List<AccountingVoucher> oldRow = AccountingVoucherMapper.selectByExample( example);
        for(int i=0;i<oldRow.size();i++)
        {
            BigDecimal  addMoney = BigDecimal.valueOf(0);
            if("借".equals(aType) && null !=oldRow.get(i).getMoneyD())
            {
                addMoney = oldRow.get(i).getMoneyD();
            }
            if("贷".equals(aType) && null !=oldRow.get(i).getMoneyC())
            {
                addMoney = oldRow.get(i).getMoneyC();
            }
            myMoney = myMoney.add(addMoney);

        }

        return myMoney;
    }


    @Override
    // 本期借贷合计数
    public BigDecimal sumCash(String userTeam ,int period) {
        BigDecimal myMoney = BigDecimal.valueOf(0);
        BigDecimal moneyD = BigDecimal.valueOf(0);
        BigDecimal moneyC = BigDecimal.valueOf(0);


        PageData pd = new PageData();
        pd.put("teamcount",userTeam);
        pd.put("period",period);
        pd.put("acode","现金");

        PageData myResult = new PageData();
        myResult =  AccountingVoucherMapper.sumCashDAndC(pd);

        if(myResult != null) {
            moneyD = BigDecimal.valueOf(Double.valueOf(myResult.getObjectToString("moneyD")));
            moneyC = BigDecimal.valueOf(Double.valueOf(myResult.getObjectToString("moneyC")));
            myMoney = moneyD.subtract(moneyC);//借方-贷方
        }

        return myMoney;
    }



    // 本期借贷合计数
    public List<PageData> sumDAndCByCode(String userTeam ,int period) {
        PageData pd = new PageData();
        pd.put("teamcount",userTeam);
        pd.put("period",period);
        return AccountingVoucherMapper.sumDAndCByCode(pd);  //通过自定义SQL查询各会计科目的借、贷方合计金额。
    }

    @Override
    public PageInfo<AccountingVoucher> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AccountingVoucherMapper.selectAll());
    }

    @Override
    public AccountingVoucher getById(String id) {
        Example example = new Example(AccountingVoucher.class);
        example.createCriteria().andEqualTo("id", id);
        return AccountingVoucherMapper.selectOneByExample(example);
    }

    private  void  voucherMakerBase(String teamCount, int period ,String debtAcount,String CreditAcount, BigDecimal amount,String content)
    {
        if ( content != "期间损益结转"){

        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", teamCount);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("substract", content);
        List<AccountingVoucher> oldRow1 = AccountingVoucherMapper.selectByExample(example);
        if (oldRow1.size() > 0) {
            AccountingVoucherMapper.deleteByExample(example);
        }
    }
        //插入会计分录-借方
        AccountingVoucher vd1 = new AccountingVoucher();
        vd1.setTeamCount(teamCount);
        vd1.setPeriod(period);
        vd1.setSubstract(content);
        vd1.setAcode(debtAcount);
        vd1.setAname(debtAcount);
        vd1.setMoneyD(amount);
        BaseBeanHelper.insert(vd1);
        AccountingVoucherMapper.insert(vd1);
         //插入会计分录-贷方
        AccountingVoucher vc1 = new AccountingVoucher();
        vc1.setTeamCount(teamCount);
        vc1.setPeriod(period);
        vc1.setSubstract(content);
        vc1.setAcode(CreditAcount);
        vc1.setAname(CreditAcount);
        vc1.setMoneyC(amount);
        BaseBeanHelper.insert(vc1);
        AccountingVoucherMapper.insert(vc1);

    }

   //yang 一个巨长的逻辑代码：自动记账
    public void voucherMaker(String teamCount, int period , BigDecimal amount, String voucherType,String content) {
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        switch (voucherType)
        {
           case "CHANGDAI":  //长贷的自动会计分录 借现金 贷长期负债
               voucherMakerBase(teamCount,period,"现金","长期负债",amount,content);
               break;

           case "CHCD":  //还长贷的自动会计分录 借长期负债 贷现金
                voucherMakerBase(teamCount,period,"长期负债","现金",amount,content);
                break;

           case "DUANDAI":  //短贷的会计分录  借现金 贷长期负债
               voucherMakerBase(teamCount,period,"现金","短期负债",amount,content);
               break;

           case "CHDD":  //还短贷的自动会计分录  借短期负债 贷现金
               voucherMakerBase(teamCount,period,"短期负债","现金",amount,content);
                break;
           case "GAOLIDAI":  //高利贷的会计分录 借现金 贷应付
                voucherMakerBase(teamCount,period,"现金","应付账款",amount,content);
                break;

           case "CHGLD":  //高利贷的会计分录 借应付 贷现金
                voucherMakerBase(teamCount,period,"应付账款","现金",amount,content);
                break;

           case "CPYF": //产品研发的会计分录 借综合费用（研发费用） 贷现金
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                 break;
           case "SCKF":  //市场研发的会计分录 借综合费用（研发费用） 贷现金
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;
           case "ISOZZ": //ISO认证的会计分录  借综合费用（研发费用） 贷现金
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
               break;

            //产品交货-赊销，生成主营业务收入，应收账款的会计分录（账期>0,赊销交货）
           case "JH": //交货的会计分录
                voucherMakerBase(teamCount,period,"应收账款","销售收入",amount,content);
               break;

            //产品交货-现款，生成主营业务收入，现金的会计分录（账期为0,现款交货）
            case "JHXK": //交货的会计分录
                voucherMakerBase(teamCount,period,"现金","销售收入",amount,content);
                break;

            //原材料入库，生成借原料，贷现金的会计分录
           case "CLRK": //材料入库的会计分录
                voucherMakerBase(teamCount,period,"原料","现金",amount,content);
               break;


            //销售收款，生成借现金，贷应收款的会计分录
           case "XSSK":
                voucherMakerBase(teamCount,period,"现金","应收账款",amount,content);
                break;

            //销售出库。 借直接成本 贷成品
           case "XSCK":
                voucherMakerBase(teamCount,period,"直接成本","成品",amount,content);
                break;


            //出售厂房，生成借应收款，贷土地与建筑的会计分录
           case "CSCF": //出售厂房的会计分录
                voucherMakerBase(teamCount,period,"应收账款","土地与建筑",amount,content);
                break;

            //租赁厂房，生成借综合费用（租金），贷现金的会计分录
            case "CFZL": //租赁厂房的会计分录
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;

            //购买厂房，生成借土地与建筑，贷现金的会计分录
           case "GMCF": //购买厂房的会计分录
                voucherMakerBase(teamCount,period,"土地与建筑","现金",amount,content);
                break;


            //生产线建造，生成借在建工程，贷现金的会计分录
            case "ZJGC":
                voucherMakerBase(teamCount,period,"在建工程","现金",amount,content);
               break;
            //生产线建造完成。在建工程转出到“机器与设备”
            case "ZJGCZC":
                voucherMakerBase(teamCount,period,"机器与设备","在建工程",amount,content);
                break;
                //生产线维修费用合计记入综合费用（维修费用）
            case "WXFY":
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;

            //生产领料。借在制品 贷原材
            case "SCCK":
                voucherMakerBase(teamCount,period,"在制品","原料",amount,content);

                break;
            //生产人工费。借在制品 贷现金
            case "SCRGF":
                voucherMakerBase(teamCount,period,"在制品","现金",amount,content);
                break;
            //产品入库。借成品 贷在制品
            case "CPRK":
                voucherMakerBase(teamCount,period,"成品","在制品",amount,content);
                break;

            //卖生产线收回残值。借现金 贷机器与设备
            case "SHCZ":
                voucherMakerBase(teamCount,period,"现金","机器与设备",amount,content);
                break;

            //卖出生产线的损失的会计凭证.借其它支出贷机器与设备
            case "SS":
                voucherMakerBase(teamCount,period,"其他支出","机器与设备",amount,content);
                break;

             //自动生成转产费用的凭证。借综合费用（转产费） 贷现金
            case "SCXZC":
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;
                //H 管理费用结转 借综合费用（管理费用） 贷现金
            case "GLFY":
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;
                //H 折旧费用结转 借折旧费用 贷机器与设备
            case "ZJFY":
                voucherMakerBase(teamCount,period,"折旧费用","机器与设备",amount,content);
                break;
                //H 应收账款贴现 借现金，财务支出（贴息）  贷应收账款
            case "YSZKTX":
                voucherMakerBase(teamCount,period,"现金","应收账款",amount,content);
                break;

            case "YSZKTX2":
                voucherMakerBase(teamCount,period,"财务支出","应收账款",amount,content);
                break;
            //H 广告费 借综合费用（广告费） 贷现金
            case "GGF":
                voucherMakerBase(teamCount,period,"综合费用","现金",amount,content);
                break;
                //H 利息费用结转 借 财务支出（利息） 贷现金
            case "LXFY":
                voucherMakerBase(teamCount,period,"财务支出","现金",amount,content);
                break;
                //交税 借应交税金 贷现金
            case "JS":
                voucherMakerBase(teamCount,period,"应交税金","现金",amount,content);
                break;
                //H 所得税结转 借所得税 贷应交税金
            case "SDS":
                voucherMakerBase(teamCount,period,"所得税","应交税金",amount,content);
                break;

                //H 期间损益结转 跟后面代码有冲突先加上小标
            case "XSXRE":
                voucherMakerBase(teamCount,period,"销售收入","本年利润",amount,"期间损益结转");
                break;
            case "ZJCBE":
                voucherMakerBase(teamCount,period,"本年利润","直接成本",amount,"期间损益结转");
                break;
            case "ZHFYE":
                voucherMakerBase(teamCount,period,"本年利润","综合费用",amount,"期间损益结转");
                break;
            case "ZJFYE":
                voucherMakerBase(teamCount,period,"本年利润","折旧费用",amount,"期间损益结转");
                break;
            case "CWFYE":
                voucherMakerBase(teamCount,period,"本年利润","财务支出",amount,"期间损益结转");
                break;
            case "QTFYE":
                voucherMakerBase(teamCount,period,"本年利润","其他支出",amount,"期间损益结转");
                break;
            case "SDSFYE":
                voucherMakerBase(teamCount,period,"本年利润","所得税",amount,"期间损益结转");
                break;
            case "BNLRE":
                voucherMakerBase(teamCount,period,"本年利润","年度净利",amount,"期间损益结转");
                break;
           case "LRLCE":
                voucherMakerBase(teamCount,period,"年度净利","利润留存",amount,content);
                break;

     }

    }


    @Override
    public void deleteByPeriodAndContent(String userTeam,Integer period,String content) {

        //删除当前期间某一项的凭证记录
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("substract", content);
        List<AccountingVoucher> oldRow1 = AccountingVoucherMapper.selectByExample(example);
        if(oldRow1.size() > 0)
        {
            AccountingVoucherMapper.deleteByExample(example);
        }
    }



    @Override
    public void deleteByTeamCount(String userTeam) {

        //用于初始化，清空会计凭证
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        List<AccountingVoucher> oldRow1 = AccountingVoucherMapper.selectByExample(example);
        if(oldRow1.size() > 0)
        {
            AccountingVoucherMapper.deleteByExample(example);
        }
    }

    @Override
    public List<AccountingVoucher> selectByPeriodAndUserTeam( String userTeam,Integer period) {

        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        return(AccountingVoucherMapper.selectByExample(example));
    }


    @Override
    public BigDecimal selectByPeriodAndUserTeamAndContent( String userTeam,Integer period,String content,String acode) {

        Integer periodS=period%4!=0?period/4*4+1:(period/4-1)*4+1;
        Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        /*criteria.andEqualTo("period", period); //上一个周期*/

        criteria.andBetween("period",periodS,period); //查询区间，计算本年度到目前为止的发生额
        criteria.andEqualTo("substract",content);
        criteria.andEqualTo("acode",acode);
       List< AccountingVoucher> oldRow =AccountingVoucherMapper.selectByExample(example);
       BigDecimal sum=BigDecimal.valueOf(0);

           if (oldRow.size() > 0) {
               for (int i = 0; i <= period -periodS; i++) {
                   sum=sum.add(oldRow.get(i).getMoneyD());
               }
               return (sum); //返回合计值
           }
           else{
               return (null);
           }
    }

    @Override
    public List<BigDecimal> listForExpense( String userTeam,Integer period) {
        //得到当前期间对应的年度第1季到当前季的前一季度。
        Integer periodE = period-1;
        Integer periodS=periodE%4!=0?periodE/4*4+1:(periodE/4-1)*4+1;


        String contentWX= "维修费用合计";
        String contentZJ="计提折旧费用";
        String contentBG="管理费用";
        String contentSDS="所得税费用";
        String contentGG="本期广告费合计";
        String acodeWX="综合费用";
        String acodeZJ="折旧费用";
        String acodeBG="综合费用";
        String acodeSDS="所得税";
        String acodeGG="综合费用";
        BigDecimal sumWX=BigDecimal.valueOf(0);
        BigDecimal sumZJ=BigDecimal.valueOf(0);
        BigDecimal sumBG=BigDecimal.valueOf(0);
        BigDecimal sumSDS=BigDecimal.valueOf(0);
        BigDecimal sumGG=BigDecimal.valueOf(0);

        PageData pd = new PageData();
        pd.put("teamcount",userTeam);
        pd.put("periodS",periodS);
        pd.put("period",period);

        List<PageData> myResult = AccountingVoucherMapper.sumExpenseBySubAndCode(pd);


    /*  以下算法效率不高，弃用  Example example = new Example(AccountingVoucher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andBetween("period",periodS,period); //查询区间，计算本年度到目前为止的发生额

        List< AccountingVoucher> myRows =AccountingVoucherMapper.selectByExample(example);*/
/*
        if (myRows.size() > 0) {
            for (int i = 0; i < myRows.size(); i++) {
               if(contentWX.equals(myRows.get(i).getSubstract()) && acodeWX.equals(myRows.get(i).getAcode()))
               {sumWX = sumWX.add(myRows.get(i).getMoneyD()); }

                if(contentGG.equals(myRows.get(i).getSubstract()) && acodeGG.equals(myRows.get(i).getAcode()))
                {sumGG = sumGG.add(myRows.get(i).getMoneyD()); }

                if(contentBG.equals(myRows.get(i).getSubstract()) && acodeBG.equals(myRows.get(i).getAcode()))
                {sumBG = sumBG.add(myRows.get(i).getMoneyD()); }

                if(contentZJ.equals(myRows.get(i).getSubstract()) && acodeZJ.equals(myRows.get(i).getAcode()))
                {sumZJ = sumZJ.add(myRows.get(i).getMoneyD()); }

                if(contentSDS.equals(myRows.get(i).getSubstract()) && acodeSDS.equals(myRows.get(i).getAcode()))
                {sumSDS = sumSDS.add(myRows.get(i).getMoneyD()); }


            }

        }*/

        if (myResult.size() > 0) {
            for (int i = 0; i < myResult.size(); i++) {
                if(contentWX.equals(myResult.get(i).getString("substract")) && acodeWX.equals(myResult.get(i).getString("acode")))
                {sumWX = BigDecimal.valueOf(Double.valueOf(myResult.get(i).getObjectToString("moneyD"))); }

                if(contentGG.equals(myResult.get(i).getString("substract")) && acodeGG.equals(myResult.get(i).getString("acode")))
                {sumGG = BigDecimal.valueOf(Double.valueOf(myResult.get(i).getObjectToString("moneyD"))); }

                if(contentBG.equals(myResult.get(i).getString("substract")) && acodeBG.equals(myResult.get(i).getString("acode")))
                {sumBG = BigDecimal.valueOf(Double.valueOf(myResult.get(i).getObjectToString("moneyD"))); }

                if(contentZJ.equals(myResult.get(i).getString("substract")) && acodeZJ.equals(myResult.get(i).getString("acode")))
                {sumZJ = BigDecimal.valueOf(Double.valueOf(myResult.get(i).getObjectToString("moneyD"))); }

                if(contentSDS.equals(myResult.get(i).getString("substract")) && acodeSDS.equals(myResult.get(i).getString("acode")))
                {sumSDS = BigDecimal.valueOf(Double.valueOf(myResult.get(i).getObjectToString("moneyD"))); }


            }

        }



        List<BigDecimal> list= new ArrayList<BigDecimal>();
        list.add(new BigDecimal(0));

        list.add(1,sumWX);
        list.add(2,sumGG);
        list.add(3,sumBG);
        list.add(4,sumZJ);
        list.add(5,sumSDS);
        return list;


    }





}
