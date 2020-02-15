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
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class LongTermLoansServiceImpl implements LongTermLoansService {

    @Autowired
    private LongTermLoansMapper LongTermLoansMapper;


    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(LongTermLoans LongTermLoans) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        LongTermLoans.setTeamCount(userTeam);
        LongTermLoans.setGroupId("1000");
        LongTermLoans.setReturnTime(LongTermLoans.getPeriod()+20);

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

    @Override
    public void delete(String id) {
    LongTermLoansMapper.deleteByPrimaryKey(id);
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

}
