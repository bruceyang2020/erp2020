package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LoanType;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.LoanTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoanTypeServiceImpl implements LoanTypeService {

    @Autowired
    private LoanTypeMapper LoanTypeMapper;

    @Transactional
    @Override
    public void add(LoanType LoanType) {
        BaseBeanHelper.insert(LoanType);
        LoanTypeMapper.insert(LoanType);
    }

    @Override
    public void delete(String id) {
    LoanTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(LoanType LoanType) {
        BaseBeanHelper.edit(LoanType);
        Example example = new Example(LoanType.class);
        example.createCriteria().andEqualTo("id", LoanType.getId());
        LoanTypeMapper.updateByExampleSelective(LoanType, example);
    }

    @Override
    public PageInfo<LoanType> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(LoanTypeMapper.selectAll());
    }

    @Override
    public LoanType getById(String id) {
        Example example = new Example(LoanType.class);
        example.createCriteria().andEqualTo("id", id);
        return LoanTypeMapper.selectOneByExample(example);
    }
}
