package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.LongTermLoansMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.lang.model.element.VariableElement;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class LongTermLoansServiceImpl implements LongTermLoansService {

    @Autowired
    private LongTermLoansMapper LongTermLoansMapper;


    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Resource
    private LongTermLoansService longTermLoansService;

    @Transactional
    @Override
    public void add(LongTermLoans LongTermLoans) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        LongTermLoans.setTeamCount(userTeam);
        LongTermLoans.setGroupId("1000");
        LongTermLoans.setRate(new BigDecimal (0.1));   //长贷利率10%
        LongTermLoans.setReturnTime(21);  //长贷回收的时间
        LongTermLoans.setPeriodLoan(LongTermLoans.getPeriod());  //长贷生成的时间

        //删除当前长贷记录
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", LongTermLoans.getTeamCount());
        criteria.andEqualTo("periodLoan", LongTermLoans.getPeriod()); //H 借款的时间不能相同
        List<LongTermLoans> oldRow = LongTermLoansMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            LongTermLoansMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(LongTermLoans);
        LongTermLoansMapper.insert(LongTermLoans);

        //自动生成长贷会计凭证
        accountingVoucherService.voucherMaker(userTeam,LongTermLoans.getPeriod(),LongTermLoans.getMoneyTotal(),"CHANGDAI","新增长贷");


    }

    public void adds(List<LongTermLoans>  longTermLoansList) {
        if(longTermLoansList.size() > 0) {
            for (int i = 0; i < longTermLoansList.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                longTermLoansList.get(i).setPeriod(1);
                longTermLoansList.get(i).setTeamCount(userTeam);
                longTermLoansList.get(i).setGroupId("1000");
                BaseBeanHelper.insert(longTermLoansList.get(i));
                LongTermLoansMapper.insert(longTermLoansList.get(i));
            }
        }
    }


    @Override
    public void delete(String id) {
    LongTermLoansMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        LongTermLoansMapper.deleteByExample(example);
    }


    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        LongTermLoansMapper.deleteByExample(example);

    }


    @Override
    public void update(LongTermLoans LongTermLoans) {
        BaseBeanHelper.edit(LongTermLoans);
        Example example = new Example(LongTermLoans.class);
        example.createCriteria().andEqualTo("id", LongTermLoans.getId());
        LongTermLoansMapper.updateByExampleSelective(LongTermLoans, example);
    }

    @Override
    public PageInfo<LongTermLoans> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(LongTermLoansMapper.selectAll());
    }

    @Override
    public LongTermLoans getById(String id) {
        Example example = new Example(LongTermLoans.class);
        example.createCriteria().andEqualTo("id", id);
        return LongTermLoansMapper.selectOneByExample(example);
    }
    @Override
    public List<LongTermLoans> list() {
        return LongTermLoansMapper.selectAll();
    }


    @Override
    public List<LongTermLoans> getByUserTeamIdAndPeriod(String userTeam,int period ) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period",period);

        return LongTermLoansMapper.selectByExample(example);
    }

    //H一年后长贷还还息，记入的报表时间为结转后的period，即nextPeriod
    @Override
    public void voucherMakerOfInterestAndRepayment(String userTeam,int nextPeriod) {
       // 每年初计一次
        if(nextPeriod%4==1) {
            //H 按teamCount，period取出所有长贷
            Example example = new Example(LongTermLoans.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("teamCount", userTeam);
            criteria.andEqualTo("period",nextPeriod);
            List<LongTermLoans> myList = LongTermLoansMapper.selectByExample(example);
            BigDecimal longTermLoansInterest = BigDecimal.valueOf(0);
            for (int i = 0; i < myList.size(); i++) {
                    //H 每一年结转时还款期减4
                    myList.get(i).setReturnTime(myList.get(i).getReturnTime() - 4);
                    BaseBeanHelper.edit(myList.get(i));
                    LongTermLoansMapper.updateByPrimaryKey(myList.get(i));
                    if(myList.get(i).getReturnTime()>0) {
                        //H 每年初还息,去除偿还贷款还继续还息的bug
                        longTermLoansInterest = longTermLoansInterest.add(myList.get(i).getMoneyTotal().multiply(BigDecimal.valueOf(0.1)).setScale(0, BigDecimal.ROUND_DOWN));
                    }
                    if (myList.get(i).getReturnTime() == 1) {
                        //H 到期年初偿还本金
                        accountingVoucherService.voucherMaker(userTeam, nextPeriod, myList.get(i).getMoneyTotal(), "CHCD", "偿还长期贷款");
                    }
            }
            //H 利息记账
            accountingVoucherService.voucherMaker(userTeam, nextPeriod, longTermLoansInterest, "LXFY", "长期贷款利息");
        }
    }


    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<LongTermLoans> factorys = LongTermLoansMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                LongTermLoans myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                BaseBeanHelper.insert(myRow);
                LongTermLoansMapper.insert(myRow);

            }
        }

    }

}
