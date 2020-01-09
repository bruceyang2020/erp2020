package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ProductLineMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    private ProductLineMapper ProductLineMapper;

    @Transactional
    @Override
    public void add(ProductLine ProductLine) {
        BaseBeanHelper.insert(ProductLine);
        ProductLineMapper.insert(ProductLine);
    }

    @Override
    public void delete(String id) {
    ProductLineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductLine ProductLine) {
        BaseBeanHelper.edit(ProductLine);
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", ProductLine.getId());
        ProductLineMapper.updateByExampleSelective(ProductLine, example);
    }

    @Override
    public PageInfo<ProductLine> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ProductLineMapper.selectAll());
    }

    @Override
    public ProductLine getById(String id) {
        Example example = new Example(ProductLine.class);
        example.createCriteria().andEqualTo("id", id);
        return ProductLineMapper.selectOneByExample(example);
    }
}
