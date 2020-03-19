package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.BalancesheetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        Example example = new Example(Balancesheet.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return BalancesheetMapper.selectOneByExample(example);
    }


    @Override
    public void createBalanceSheet(List<AccountBalance> accountBalances,String userTeam ,int period) {


        Balancesheet balancesheet = new Balancesheet();
        String acode = "";
        balancesheet.setPeriod(period);
        balancesheet.setTeamCount(userTeam);
        if(accountBalances.size() > 0) {


            for (int i = 1; i < accountBalances.size(); i++)
            {
                acode = accountBalances.get(i).getAcode();
                if("现金".equals(acode)){balancesheet.setMoneyNow(accountBalances.get(i).getMoneyE());}
                if("应收款".equals(acode)){balancesheet.setMoneyGet(accountBalances.get(i).getMoneyE());}
                if("在制品".equals(acode)){balancesheet.setMoneyMaking(accountBalances.get(i).getMoneyE());}
                if("成品".equals(acode)){balancesheet.setMoneySale(accountBalances.get(i).getMoneyE());}
                if("原料".equals(acode)){balancesheet.setMoneyBuy(accountBalances.get(i).getMoneyE());}
                if("土地与建筑".equals(acode)){balancesheet.setMoneyJ(accountBalances.get(i).getMoneyE());}
                if("机器与设备".equals(acode)){balancesheet.setMoneyP(accountBalances.get(i).getMoneyE());}
                if("在建工程".equals(acode)){balancesheet.setMoneyM(accountBalances.get(i).getMoneyE());}
                if("长期负债".equals(acode)){balancesheet.setLongTermLoan(accountBalances.get(i).getMoneyE());}
                if("短期负债".equals(acode)){balancesheet.setShortTermLoan(accountBalances.get(i).getMoneyE());}
               // if("高利贷".equals(acode)){balancesheet.setShortTermLoan(accountBalances.get(i).getMoneyE());}  高利贷记入应付账款还是短期负债？

               // if("应付账款".equals(acode)){balancesheet.setMoneyOrderGet(accountBalances.get(i).getMoneyE());}
                if("应交税金".equals(acode)){balancesheet.setMoneyTax(accountBalances.get(i).getMoneyE());}
                if("股东资本".equals(acode)){balancesheet.setMoneyG(accountBalances.get(i).getMoneyE());}
                if("利润留存".equals(acode)){balancesheet.setMoneyStay(accountBalances.get(i).getMoneyE());}
                if("年度净利".equals(acode)){balancesheet.setMoneyYear(accountBalances.get(i).getMoneyC());}//H 这是本期发生额



            }
          /*  balancesheet.setMoneyFlow(balancesheet.getMoneyNow().add(balancesheet.getMoneyGet().add(balancesheet.getMoneyMaking().add(balancesheet.getMoneySale().add(balancesheet.getMoneyBuy()))))); //流动资产
            balancesheet.setMoneyStatic(balancesheet.getMoneyJ().add(balancesheet.getMoneyP().add(balancesheet.getMoneyM()))); //固定资产
            balancesheet.setMoneyAll(balancesheet.getMoneyFlow().add(balancesheet.getMoneyStatic())); //总资产合计
            //balancesheet.setMoneyLoan(balancesheet.getLongTermLoan().add(balancesheet.getShortTermLoan().add(balancesheet.getMoneyTax()))); //负债(应付账款没写)
            balancesheet.setMoneyUser(balancesheet.getMoneyG().add(balancesheet.getMoneyStay().add(balancesheet.getMoneyYear()))); //所有者权益
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
