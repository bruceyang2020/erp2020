package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MaterialOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialOrderServiceImpl implements MaterialOrderService {

    @Autowired
    private MaterialOrderMapper MaterialOrderMapper;

    @Transactional
    @Override
    public void add(MaterialOrder MaterialOrder) {
        BaseBeanHelper.insert(MaterialOrder);
        MaterialOrderMapper.insert(MaterialOrder);
    }

    @Override
    public void delete(String id) {
    MaterialOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MaterialOrder MaterialOrder) {
        BaseBeanHelper.edit(MaterialOrder);
        Example example = new Example(MaterialOrder.class);
        example.createCriteria().andEqualTo("id", MaterialOrder.getId());
        MaterialOrderMapper.updateByExampleSelective(MaterialOrder, example);
    }

    @Override
    public PageInfo<MaterialOrder> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MaterialOrderMapper.selectAll());
    }

    @Override
    public MaterialOrder getById(String id) {
        Example example = new Example(MaterialOrder.class);
        example.createCriteria().andEqualTo("id", id);
        return MaterialOrderMapper.selectOneByExample(example);
    }
}
