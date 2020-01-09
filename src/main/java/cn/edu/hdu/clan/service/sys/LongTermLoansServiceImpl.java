package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.LongTermLoansMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class LongTermLoansServiceImpl implements LongTermLoansService {

    @Autowired
    private LongTermLoansMapper LongTermLoansMapper;

    @Transactional
    @Override
    public void add(LongTermLoans LongTermLoans) {
        BaseBeanHelper.insert(LongTermLoans);
        LongTermLoansMapper.insert(LongTermLoans);
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
    public List<LongTermLoans> getByUserIdAndPeriod(String create_user) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", create_user);

        return LongTermLoansMapper.selectByExample(example);
    }

}
