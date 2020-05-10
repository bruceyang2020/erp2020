package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.BalancesheetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BalancesheetServiceImpl implements BalancesheetService {

    @Autowired
    private BalancesheetMapper BalancesheetMapper;
    @Resource
    private  AccountingVoucherService accountingVoucherService;
    @Transactional
    @Override
    public void add(Balancesheet Balancesheet) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Balancesheet.setPeriod(period-1);
        Balancesheet.setTeamCount(userTeam);
        BaseBeanHelper.insert(Balancesheet);
        BalancesheetMapper.insert(Balancesheet);

    }

    public void adds(List<Balancesheet>  balancesheets) {
        if(balancesheets.size() > 0) {
            for (int i = 0; i < balancesheets.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                balancesheets.get(i).setPeriod(period);
                balancesheets.get(i).setTeamCount(userTeam);
                BaseBeanHelper.insert(balancesheets.get(i));
                BalancesheetMapper.insert(balancesheets.get(i));
            }
        }
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Balancesheet.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        BalancesheetMapper.deleteByExample(example);
    }



    @Override
    public void delete(String id) {
    BalancesheetMapper.deleteByPrimaryKey(id);
    }




    @Override
    public void update(Balancesheet Balancesheet) {
//        BaseBeanHelper.edit(Balancesheet);
        Example example = new Example(Balancesheet.class);
        example.createCriteria().andEqualTo("id", Balancesheet.getId());
        BalancesheetMapper.updateByExampleSelective(Balancesheet, example);
    }

    @Override
    public PageInfo<Balancesheet> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(BalancesheetMapper.selectAll());
    }

    @Override
    public List<Balancesheet> list() {
        return BalancesheetMapper.selectAll();
    }

    @Override
    public Balancesheet getById(String id) {
        Example example = new Example(Balancesheet.class);
        example.createCriteria().andEqualTo("id", id);
        return BalancesheetMapper.selectOneByExample(example);
    }


    @Override
    public List<Balancesheet> getByUserIdAndPeriod(String create_user,int period) {
        Example example = new Example(Balancesheet.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", create_user);
        criteria.andEqualTo("period", period);
        return BalancesheetMapper.selectByExample(example);
    }

    @Override
    public Balancesheet getByUserTeamAndPeriod(String userTeam,int period) {
        //H period: 1 5 9 13 17
        // 0 4 8 12 16  找不到4 找3

        for(int i=1;i<=4;i++) {
            Example example = new Example(Balancesheet.class);
            Criteria criteria = example.createCriteria();
            criteria.andEqualTo("teamCount", userTeam);
            criteria.andEqualTo("period", period-i);
            Balancesheet oldRow = BalancesheetMapper.selectOneByExample(example);
            if (oldRow!=null) {
                return oldRow;
            }
        }
        return null;
    }


    @Override
    public void createBalanceSheet(List<AccountBalance> accountBalances,String userTeam ,int period) {


        Balancesheet balancesheet = new Balancesheet();
        String acode = "";
        balancesheet.setPeriod(period);
        balancesheet.setTeamCount(userTeam);
        if(accountBalances.size() > 0) {


            for (int i = 0; i < accountBalances.size(); i++)
            {
                acode = accountBalances.get(i).getAcode();
                BigDecimal moneye=BigDecimal.valueOf(0);
                moneye=moneye.add(accountBalances.get(i).getMoneyE());
                switch(acode) {
                    case "现金":
                        balancesheet.setMoneyNow(moneye);
                        break;
                    case "应收账款":
                        balancesheet.setMoneyGet(moneye);
                        break;
                    case "在制品":
                        balancesheet.setMoneyMaking(moneye);
                        break;
                    case "成品":
                        balancesheet.setMoneySale(moneye);
                        break;
                    case "原料":
                        balancesheet.setMoneyBuy(moneye);
                        break;
                    case "土地与建筑":
                        balancesheet.setMoneyJ(moneye);
                        break;
                    case "机器与设备":
                        balancesheet.setMoneyP(moneye);
                        break;
                    case "在建工程":
                        balancesheet.setMoneyM(moneye);
                        break;
                    case "长期负债":
                        balancesheet.setLongTermLoan(moneye);
                        break;
                    case "短期负债":
                        balancesheet.setShortTermLoan(moneye);
                        break;
                    case "应付账款":
                        balancesheet.setMoneyOrderGet(moneye);//H 高利贷记入应付账款
                        break;
                    case "应交税金":
                        balancesheet.setMoneyTax(moneye);
                        break;
                    case "股东资本":
                        balancesheet.setMoneyG(moneye);
                        break;
                    case "利润留存":
                        balancesheet.setMoneyStay(moneye);
                        break;
                    case "年度净利":
                        balancesheet.setMoneyYear(accountBalances.get(i).getMoneyC());//H 这是本期发生额
                        break;
                }

                /*if("现金".equals(acode)==true){balancesheet.setMoneyNow(accountBalances.get(i).getMoneyE());}
                if("应收款".equals(acode)==true){balancesheet.setMoneyGet(accountBalances.get(i).getMoneyE());}
                if("在制品".equals(acode)==true){balancesheet.setMoneyMaking(accountBalances.get(i).getMoneyE());}
                if("成品".equals(acode)==true){balancesheet.setMoneySale(accountBalances.get(i).getMoneyE());}
                if("原料".equals(acode)==true){balancesheet.setMoneyBuy(accountBalances.get(i).getMoneyE());}
                if("土地与建筑".equals(acode)==true){balancesheet.setMoneyJ(accountBalances.get(i).getMoneyE());}
                if("机器与设备".equals(acode)==true){balancesheet.setMoneyP(accountBalances.get(i).getMoneyE());}
                if("在建工程".equals(acode)==true){balancesheet.setMoneyM(accountBalances.get(i).getMoneyE());}
                if("长期负债".equals(acode)==true){balancesheet.setLongTermLoan(accountBalances.get(i).getMoneyE());}
                if("短期负债".equals(acode)==true){balancesheet.setShortTermLoan(accountBalances.get(i).getMoneyE());}
                if("应付账款".equals(acode)==true){balancesheet.setMoneyOrderGet(accountBalances.get(i).getMoneyE());}  //H 高利贷记入应付账款

               // if("应付账款".equals(acode)){balancesheet.setMoneyOrderGet(accountBalances.get(i).getMoneyE());}
                if("应交税金".equals(acode)==true){balancesheet.setMoneyTax(accountBalances.get(i).getMoneyE());}
                if("股东资本".equals(acode)==true){balancesheet.setMoneyG(accountBalances.get(i).getMoneyE());}
                if("利润留存".equals(acode)==true){balancesheet.setMoneyStay(accountBalances.get(i).getMoneyE());}
                if("年度净利".equals(acode)==true){balancesheet.setMoneyYear(accountBalances.get(i).getMoneyC());}//H 这是本期发生额*/

            }

            BigDecimal moneyNow = balancesheet.getMoneyNow();
            BigDecimal moneyGet = balancesheet.getMoneyGet();
            BigDecimal moneyMaking = balancesheet.getMoneyMaking();
            BigDecimal moneySale = balancesheet.getMoneySale();
            BigDecimal moneyBuy = balancesheet.getMoneyBuy();

            System.out.print("begin");
            System.out.print("现金"+moneyNow);
            System.out.print("应收款"+moneyGet);
            System.out.print("在制品"+moneyMaking);
            System.out.print("成品"+moneySale);
            System.out.print("材料"+moneyBuy);
            System.out.print("end");
            balancesheet.setMoneyFlow(moneyNow.add(moneyGet).add(moneyMaking).add(moneySale).add(moneyBuy));
            BigDecimal moneyFlow = balancesheet.getMoneyFlow();
            // Y 流动资产

            BigDecimal moneyJ= balancesheet.getMoneyJ();
            System.out.print("建筑"+moneyJ);
            BigDecimal moneyP= balancesheet.getMoneyP();
            System.out.print("设备"+moneyP);
            BigDecimal moneyM = balancesheet.getMoneyM();
            System.out.print("在建工程"+moneyM);

            balancesheet.setMoneyStatic(moneyJ.add(moneyP).add(moneyM));
            BigDecimal moneyStatic = balancesheet.getMoneyStatic();
            System.out.print("固定资产"+moneyStatic);
            //固定资产

            // Y 总资产合计
            balancesheet.setMoneyAll(moneyFlow.add(moneyStatic));


            BigDecimal longTermLoan = balancesheet.getLongTermLoan();
            System.out.print("长期负债"+longTermLoan);
            BigDecimal shortTermLoan = balancesheet.getShortTermLoan();
            System.out.print("短期负债"+shortTermLoan);
            BigDecimal moneyTax = balancesheet.getMoneyTax();
            System.out.print("应交税金"+moneyTax);
            BigDecimal moneyOrder = balancesheet.getMoneyOrderGet();
            System.out.print("应交税金"+moneyOrder);
            balancesheet.setMoneyLoan(longTermLoan.add(shortTermLoan).add(moneyTax).add(moneyOrder)); //负债(应付账款没写)
            //负债

            BigDecimal moneyG = balancesheet.getMoneyG();
            System.out.print("股东权益"+moneyG);
            BigDecimal moneyStay = balancesheet.getMoneyStay();
            System.out.print("利润留存"+moneyStay);
            BigDecimal moneyYear = balancesheet.getMoneyYear();
            System.out.print("年度净利"+moneyYear);
            balancesheet.setMoneyUser(moneyG.add(moneyStay).add(moneyYear));
            //所有者权益


            BigDecimal moneyUser = balancesheet.getMoneyUser();
            if(moneyUser == null) {
                moneyUser = BigDecimal.valueOf(0);
            }
            BigDecimal moneyLoan= balancesheet.getMoneyLoan();
            if(moneyLoan == null) {
                moneyLoan = BigDecimal.valueOf(0);
            }
            balancesheet.setMoneyAlls(moneyUser.add(moneyLoan)); //负债与所有者权益合计
           /* balancesheet.setMoneyFlow(balancesheet.getMoneyNow().add(balancesheet.getMoneyGet().add(balancesheet.getMoneyMaking().add(balancesheet.getMoneySale().add(balancesheet.getMoneyBuy()))))); //流动资产
            balancesheet.setMoneyStatic(balancesheet.getMoneyJ().add(balancesheet.getMoneyP().add(balancesheet.getMoneyM()))); //固定资产

            balancesheet.setMoneyLoan(balancesheet.getLongTermLoan().add(balancesheet.getShortTermLoan().add(balancesheet.getMoneyTax())).add(balancesheet.getMoneyOrderGet())); //负债(应付账款没写)
            balancesheet.setMoneyUser(balancesheet.getMoneyG().add(balancesheet.getMoneyStay().add(balancesheet.getMoneyYear()))); //所有者权益
            balancesheet.setMoneyAll(balancesheet.getMoneyFlow().add(balancesheet.getMoneyStatic())); //总资产合计
            balancesheet.setMoneyAlls(balancesheet.getMoneyUser().add(balancesheet.getMoneyLoan())); //负债与所有者权益合计*/
        }



        BaseBeanHelper.insert(balancesheet);
        BalancesheetMapper.insert(balancesheet);
    }

    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(Balancesheet.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<Balancesheet> factorys = BalancesheetMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                Balancesheet myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                BalancesheetMapper.insert(myRow);

            }
        }

    }
}
