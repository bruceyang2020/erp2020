package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.FactoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryMapper FactoryMapper;

    @Transactional
    @Override
    public void add(Factory Factory) {
        BaseBeanHelper.insert(Factory);
        FactoryMapper.insert(Factory);
    }

    @Override
    public void delete(String id) {
    FactoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Factory Factory) {
        BaseBeanHelper.edit(Factory);
        Example example = new Example(Factory.class);
        example.createCriteria().andEqualTo("id", Factory.getId());
        FactoryMapper.updateByExampleSelective(Factory, example);
    }

    @Override
    public PageInfo<Factory> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(FactoryMapper.selectAll());
    }

    @Override
    public Factory getById(String id) {
        Example example = new Example(Factory.class);
        example.createCriteria().andEqualTo("id", id);
        return FactoryMapper.selectOneByExample(example);
    }
}
