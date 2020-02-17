package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.util.Jurisdiction;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountBalanceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {

    @Autowired
    private AccountBalanceMapper AccountBalanceMapper;


    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(AccountBalance AccountBalance) {
        BaseBeanHelper.insert(AccountBalance);
        AccountBalanceMapper.insert(AccountBalance);
    }

    public void adds(List<AccountBalance>  accountBalanceList) {
        if(accountBalanceList.size() > 0) {
            for (int i = 0; i < accountBalanceList.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                accountBalanceList.get(i).setPeriod(period);
                accountBalanceList.get(i).setTeamCount(userTeam);
                accountBalanceList.get(i).setGroupId("1000");
                BaseBeanHelper.insert(accountBalanceList.get(i));
                AccountBalanceMapper.insert(accountBalanceList.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    AccountBalanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(AccountBalance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        AccountBalanceMapper.deleteByExample(example);
    }



    @Override
    public void deleteByPeriod(String userTeam ,int period) {
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("period", period);

        AccountBalanceMapper.deleteByExample(example);


    }

    //从当期会计凭证汇总科目发生额，填充到本期的科目余额表中
    @Override
    public void sumFromVoucher(String userTeam ,int period) {
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("period", period-1); //查询上一会计期间。period-1
        List<AccountBalance> oldRow = AccountBalanceMapper.selectByExample(example);
        if(oldRow.size() > 0) {


            for (int i = 1; i < oldRow.size(); i++)
            {
                String acode = oldRow.get(i).getAcode();
                BigDecimal moneyD = BigDecimal.valueOf(0);
                BigDecimal moneyC = BigDecimal.valueOf(0);
                String aType = oldRow.get(i).getName();  //这个字段用来标识科目的余额方向。
                BigDecimal moneyB = oldRow.get(i).getMoneyB();
                moneyD = accountingVoucherService.sumMoney(userTeam,period,acode,"借");
                moneyC = accountingVoucherService.sumMoney(userTeam,period,acode,"贷");

                AccountBalance newRow = new AccountBalance();
                newRow.setGroupId("1000");
                newRow.setTeamCount(userTeam);
                newRow.setName("yy");
                newRow.setAcode(oldRow.get(i).getAcode());
                newRow.setAname(oldRow.get(i).getAname());
                newRow.setMoneyB(oldRow.get(i).getMoneyE());  //设置当前会计科目的期初金额为上一期的期末金额。
                newRow.setMoneyD(moneyD);
                newRow.setMoneyC(moneyC);

                if("借".equals(aType)){newRow.setMoneyE(moneyB.add(moneyD).subtract(moneyC));}  //科目余额在借方的，期初+借方-贷方
                if("贷".equals(aType)){newRow.setMoneyE(moneyB.add(moneyC).subtract(moneyD));}//科目余额在贷方的，期初-借方+贷方

                newRow.setMoneyE(moneyC);
                newRow.setPeriod(period); //设置当前会计期间。

                BaseBeanHelper.insert(newRow);
                AccountBalanceMapper.insert(newRow);




            }
        }


    }

    @Override
    public void update(AccountBalance AccountBalance) {
        BaseBeanHelper.edit(AccountBalance);
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("id", AccountBalance.getId());
        AccountBalanceMapper.updateByExampleSelective(AccountBalance, example);
    }

    @Override
    public PageInfo<AccountBalance> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AccountBalanceMapper.selectAll());
    }

    @Override
    public AccountBalance getById(String id) {
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("id", id);
        return AccountBalanceMapper.selectOneByExample(example);
    }

    @Override
    public List<AccountBalance> getByTeamcountAndPeriod(String userTeam ,int period) {
        Example example = new Example(AccountBalance.class);
        example.createCriteria().andEqualTo("teamCount", userTeam);
        example.createCriteria().andEqualTo("period", period);
        return AccountBalanceMapper.selectByExample(example);
    }
}
