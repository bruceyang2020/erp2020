package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.FactoryType;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.FactoryTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class FactoryTypeServiceImpl implements FactoryTypeService {

    @Autowired
    private FactoryTypeMapper FactoryTypeMapper;

    @Transactional
    @Override
    public void add(FactoryType FactoryType) {
        BaseBeanHelper.insert(FactoryType);
        FactoryTypeMapper.insert(FactoryType);
    }

    @Override
    public void delete(String id) {
    FactoryTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(FactoryType FactoryType) {
        BaseBeanHelper.edit(FactoryType);
        Example example = new Example(FactoryType.class);
        example.createCriteria().andEqualTo("id", FactoryType.getId());
        FactoryTypeMapper.updateByExampleSelective(FactoryType, example);
    }

    @Override
    public PageInfo<FactoryType> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(FactoryTypeMapper.selectAll());
    }

    @Override
    public FactoryType getById(String id) {
        Example example = new Example(FactoryType.class);
        example.createCriteria().andEqualTo("id", id);
        return FactoryTypeMapper.selectOneByExample(example);
    }
}
