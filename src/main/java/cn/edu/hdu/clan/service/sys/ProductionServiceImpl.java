package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Production;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ProductionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private ProductionMapper ProductionMapper;

    @Transactional
    @Override
    public void add(Production Production) {
        BaseBeanHelper.insert(Production);
        ProductionMapper.insert(Production);
    }

    @Override
    public void delete(String id) {
    ProductionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Production Production) {
        BaseBeanHelper.edit(Production);
        Example example = new Example(Production.class);
        example.createCriteria().andEqualTo("id", Production.getId());
        ProductionMapper.updateByExampleSelective(Production, example);
    }

    @Override
    public PageInfo<Production> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ProductionMapper.selectAll());
    }

    @Override
    public Production getById(String id) {
        Example example = new Example(Production.class);
        example.createCriteria().andEqualTo("id", id);
        return ProductionMapper.selectOneByExample(example);
    }
}
