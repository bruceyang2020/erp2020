package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import cn.edu.hdu.clan.entity.sys.AccountBalance;

import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.IncomesheetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.*;
import java.util.List;

@Service
public class IncomesheetServiceImpl implements IncomesheetService {

    @Autowired
    private IncomesheetMapper IncomesheetMapper;

    @Transactional
    @Override
    public void add(Incomesheet Incomesheet) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        Incomesheet.setPeriod(period-1);
        Incomesheet.setTeamCount(userTeam);
        Incomesheet.setGroupId("1000");
        BaseBeanHelper.insert(Incomesheet);
        IncomesheetMapper.insert(Incomesheet);
    }


    public void adds(List<Incomesheet>  incomesheets) {
        if(incomesheets.size() > 0) {
            for (int i = 0; i < incomesheets.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                incomesheets.get(i).setPeriod(period);
                incomesheets.get(i).setTeamCount(userTeam);
                incomesheets.get(i).setGroupId("1000");
                BaseBeanHelper.insert(incomesheets.get(i));
                IncomesheetMapper.insert(incomesheets.get(i));
            }
        }
    }
    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(Incomesheet.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        IncomesheetMapper.deleteByExample(example);
    }


    @Override
    public void delete(String id) {
    IncomesheetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Incomesheet Incomesheet) {
//        BaseBeanHelper.edit(Incomesheet);
        Example example = new Example(Incomesheet.class);
        example.createCriteria().andEqualTo("id", Incomesheet.getId());
        IncomesheetMapper.updateByExampleSelective(Incomesheet, example);
    }

    @Override
    public PageInfo<Incomesheet> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(IncomesheetMapper.selectAll());
    }
    @Override
    public List<Incomesheet> list( ) {
        return IncomesheetMapper.selectAll();
    }

    @Override
    public Incomesheet getById(String id) {
        Example example = new Example(Incomesheet.class);
        example.createCriteria().andEqualTo("id", id);
        return IncomesheetMapper.selectOneByExample(example);
    }

    @Override
    public Incomesheet getByUserTeamAndPeriod(String userTeam,int period) {
        Example example = new Example(Incomesheet.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return IncomesheetMapper.selectOneByExample(example);
    }

    @Override
    public Incomesheet getincomesheet(Incomesheet incomesheet){
        return IncomesheetMapper.query();
    }

    @Override
    public void createIncomeSheet(List<AccountBalance> accountBalances,String userTeam ,int period) {

        //Y 修改利润表的字段取值。

        /*//删除已存在的利润表
        Example example = new Example(Incomesheet.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("period", period);
        IncomesheetMapper.deleteByExample(example);*/

        Incomesheet incomesheet = new Incomesheet();
        String acode ="";

        if(accountBalances.size() > 0) {

               //H 利润表表示本期发生额，应该由科目余额表借贷方显示
            for (int i = 1; i < accountBalances.size(); i++) {
                acode = accountBalances.get(i).getAcode();
                if("销售收入".equals(acode)){incomesheet.setIncomeSale(accountBalances.get(i).getMoneyC());}
                if("直接成本".equals(acode)){incomesheet.setMoneyCost(accountBalances.get(i).getMoneyD());}
                if("折旧".equals(acode)){incomesheet.setMoneyDepr(accountBalances.get(i).getMoneyD());}
                if("综合费用".equals(acode)){incomesheet.setMoneyFee(accountBalances.get(i).getMoneyD());}
                if("财务支出".equals(acode)){incomesheet.setMoneyInterest(accountBalances.get(i).getMoneyD());}
                if("其它支出".equals(acode)){incomesheet.setMoneyOther(accountBalances.get(i).getMoneyD());}
                if("所得税".equals(acode)){incomesheet.setMoneyTax(accountBalances.get(i).getMoneyD());}
            }
        }
        incomesheet.setTeamCount(userTeam);
        incomesheet.setPeriod(period);
        incomesheet.setGroupId("1000");

        BaseBeanHelper.insert(incomesheet);
        IncomesheetMapper.insert(incomesheet);

    }


    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(Incomesheet.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<Incomesheet> factorys = IncomesheetMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                Incomesheet myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                IncomesheetMapper.insert(myRow);

            }
        }

    }
}
