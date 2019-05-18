package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ResearchFeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ResearchFeeServiceImpl implements ResearchFeeService {

    @Autowired
    private ResearchFeeMapper ResearchFeeMapper;

    @Transactional
    @Override
    public void add(ResearchFee ResearchFee) {
        BaseBeanHelper.insert(ResearchFee);
        ResearchFeeMapper.insert(ResearchFee);
    }

    @Override
    public void delete(String id) {
    ResearchFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ResearchFee ResearchFee) {
        BaseBeanHelper.edit(ResearchFee);
        Example example = new Example(ResearchFee.class);
        example.createCriteria().andEqualTo("id", ResearchFee.getId());
        ResearchFeeMapper.updateByExampleSelective(ResearchFee, example);
    }

    @Override
    public PageInfo<ResearchFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ResearchFeeMapper.selectAll());
    }

    @Override
    public ResearchFee getById(String id) {
        Example example = new Example(ResearchFee.class);
        example.createCriteria().andEqualTo("id", id);
        return ResearchFeeMapper.selectOneByExample(example);
    }
}
