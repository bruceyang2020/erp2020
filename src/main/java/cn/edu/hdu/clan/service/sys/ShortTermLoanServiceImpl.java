package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ShortTermLoanMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ShortTermLoanServiceImpl implements ShortTermLoanService {

    @Autowired
    private ShortTermLoanMapper ShortTermLoanMapper;

    @Transactional
    @Override
    public void add(ShortTermLoan ShortTermLoan) {
        BaseBeanHelper.insert(ShortTermLoan);
        ShortTermLoanMapper.insert(ShortTermLoan);
    }

    @Override
    public void delete(String id) {
    ShortTermLoanMapper.deleteByPrimaryKey(id);
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
    public List<ShortTermLoan> getByUserIdAndPeriod(String create_user) {
        Example example = new Example(LongTermLoans.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", create_user);

        return ShortTermLoanMapper.selectByExample(example);
    }
}
