package cn.edu.hdu.clan.service.sys;


import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ShortTermLoanMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.alibaba.druid.sql.visitor.functions.Substring;
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
public class ShortTermLoanServiceImpl implements ShortTermLoanService {

    @Autowired
    private ShortTermLoanMapper ShortTermLoanMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(ShortTermLoan ShortTermLoan) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();

        //补充相关字段的取值
        ShortTermLoan.setTeamCount(userTeam);
        ShortTermLoan.setGroupId("1000");
        ShortTermLoan.setSurplusPeriod(5); //短期贷款剩余还款时间
        ShortTermLoan.setCurrentPeriod(ShortTermLoan.getPeriod());


        //删除当前长贷记录
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", ShortTermLoan.getTeamCount());
        criteria.andEqualTo("period", ShortTermLoan.getPeriod()); //H 判断产生短贷的会计期间
        List<ShortTermLoan> oldRow = ShortTermLoanMapper.selectByExample(example);

        if (oldRow.size() > 0) {
            ShortTermLoanMapper.deleteByExample(example);
        }
        else{
            System.out.println(oldRow);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(ShortTermLoan);
        ShortTermLoanMapper.insert(ShortTermLoan);

        //自动生成长贷会计凭证
        accountingVoucherService.voucherMaker(userTeam, ShortTermLoan.getPeriod(), ShortTermLoan.getMoneyTotal(), "DUANDAI", "新增短贷");

    }

    public void adds(List<ShortTermLoan>  shortTermLoanList) {
        if(shortTermLoanList.size() > 0) {
            for (int i = 0; i < shortTermLoanList.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                shortTermLoanList.get(i).setPeriod(1); //H 短贷的初始化的period应设置为1!!
                shortTermLoanList.get(i).setTeamCount(userTeam);
                shortTermLoanList.get(i).setGroupId("1000");
                BaseBeanHelper.insert(shortTermLoanList.get(i));
               ShortTermLoanMapper.insert(shortTermLoanList.get(i));
            }
        }
    }

    @Override
    public void delete(String id) {
        ShortTermLoanMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void deleteByTeamCount(String userTeam) {

        //用于初始化，清空短贷
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        ShortTermLoanMapper.deleteByExample(example);

    }

    @Override
    public void deleteByTeamCountAndPeriod(String userTeam, int period) {
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("currentPeriod", period);
        ShortTermLoanMapper.deleteByExample(example);

    }


    @Override
    public void update(ShortTermLoan ShortTermLoan) {
//        BaseBeanHelper.edit(ShortTermLoan);
        Example example = new Example(ShortTermLoan.class);
        example.createCriteria().andEqualTo("id", ShortTermLoan.getId());
        ShortTermLoanMapper.updateByExampleSelective(ShortTermLoan, example);
    }

    @Override
    public PageInfo<ShortTermLoan> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ShortTermLoanMapper.selectAll());
    }

    @Override
    public ShortTermLoan getById(String id) {
        Example example = new Example(ShortTermLoan.class);
        example.createCriteria().andEqualTo("id", id);
        return ShortTermLoanMapper.selectOneByExample(example);
    }

    @Override
    public List<ShortTermLoan> getByUserIdAndPeriod(String userTeam,int period) {
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("currentPeriod",period);
        return ShortTermLoanMapper.selectByExample(example);
    }

    //一年后短贷还款还息，记入的报表时间为结转后的period，即nextPeriod
    @Override
    public void voucherMakerOfInterestAndRepayment(String userTeam, int nextPeriod) {
        //H 按teamCount本期短贷
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("currentPeriod",nextPeriod);
        List<ShortTermLoan> myList = ShortTermLoanMapper.selectByExample(example);
        //H 计算利息累加,每期都有结转，利息记入当期，一期只有一笔利息

            BigDecimal shortTermLoanInterest = BigDecimal.valueOf(0);
            for (int i = 0; i < myList.size(); i++) {
                //H 每一期结转时还款期减1
                int surplusPeriod = myList.get(i).getSurplusPeriod();
                myList.get(i).setSurplusPeriod(surplusPeriod - 1);
                BaseBeanHelper.edit(myList.get(i));
                ShortTermLoanMapper.updateByPrimaryKey(myList.get(i));
                //到还款期时还息
                if (myList.get(i).getSurplusPeriod() == 1) {
                    shortTermLoanInterest = myList.get(i).getMoneyTotal().multiply(BigDecimal.valueOf(0.05)).setScale(0, BigDecimal.ROUND_DOWN);
                    //H 利息记账
                    accountingVoucherService.voucherMaker(userTeam, nextPeriod, shortTermLoanInterest, "LXFY", "短期贷款利息");
                    //H 偿还本金
                    accountingVoucherService.voucherMaker(userTeam, nextPeriod, myList.get(i).getMoneyTotal(), "CHDD", "偿还短期贷款");
                }
            }
    }


    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(ShortTermLoan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("currentPeriod", period);
        List<ShortTermLoan> factorys = ShortTermLoanMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                ShortTermLoan myRow = factorys.get(i);
                myRow.setCurrentPeriod(nextPeriod); //当前会计期间设为下一期间
                BaseBeanHelper.insert(myRow);
                ShortTermLoanMapper.insert(myRow);

            }
        }

    }
}
