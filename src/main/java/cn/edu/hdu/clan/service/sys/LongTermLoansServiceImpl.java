package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.LongTermLoans;
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
        criteria.andEqualTo("period", LongTermLoans.getPeriod());
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
                longTermLoansList.get(i).setPeriod(0); //初始化的period应设置为0，否则会被删除记录代码块删除
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
    public List<LongTermLoans> getByUserTeamIdAndPeriod(String userTeam ) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);

        return LongTermLoansMapper.selectByExample(example);
    }

    //H 长贷利息期末结转记账
    @Override
    public void voucherMakerOfInterest(String userTeam,int period) {
        if(period%4==0){
                //H 按teamCount取出所有长贷
                Example example = new Example(LongTermLoans.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("teamCount", userTeam);
                List<LongTermLoans> myList = LongTermLoansMapper.selectByExample(example);
                //H 计算利息累加
                BigDecimal longTermLoaninterest = BigDecimal.valueOf(0);
                for (int i = 0; i < myList.size(); i++) {
                    longTermLoaninterest = longTermLoaninterest.add(myList.get(i).getMoneyTotal().multiply(myList.get(i).getRate()).setScale(0, BigDecimal.ROUND_DOWN));
                }
                //H 利息记账
                accountingVoucherService.voucherMaker(userTeam, period, longTermLoaninterest, "LXFY", "长期贷款利息");
            }
    }
   //长贷在年末发生的变化,回收的期减4
    @Override
    public void goToNextPeriod(String userTeam,int nextPeriod){
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        List<LongTermLoans> myList = LongTermLoansMapper.selectByExample(example);
        for (int i = 0; i < myList.size(); i++) {
            myList.get(i).setReturnTime(5);
            System.out.println("------定时任务--------");
            System.out.println(myList.get(i).getReturnTime());

            //到期还款年初还，报表完成之后申请长贷之前
            if(myList.get(i).getReturnTime()==1){
                accountingVoucherService.voucherMaker(userTeam,nextPeriod,myList.get(i).getMoneyTotal(), "CHCD", "偿还长期贷款");
            }
        }

    }
}
