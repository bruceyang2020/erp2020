package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.IsoFeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class IsoFeeServiceImpl implements IsoFeeService {

    @Autowired
    private IsoFeeMapper IsoFeeMapper;

    @Transactional
    @Override
    public void add(IsoFee IsoFee) {
        BaseBeanHelper.insert(IsoFee);
        IsoFeeMapper.insert(IsoFee);
    }

    @Override
    public void delete(String id) {
    IsoFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(IsoFee IsoFee) {
        BaseBeanHelper.edit(IsoFee);
        Example example = new Example(IsoFee.class);
        example.createCriteria().andEqualTo("id", IsoFee.getId());
        IsoFeeMapper.updateByExampleSelective(IsoFee, example);
    }

    @Override
    public PageInfo<IsoFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(IsoFeeMapper.selectAll());
    }

    @Override
    public IsoFee getById(String id) {
        Example example = new Example(IsoFee.class);
        example.createCriteria().andEqualTo("id", id);
        return IsoFeeMapper.selectOneByExample(example);
    }
}
