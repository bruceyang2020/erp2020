package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ModuleOrder;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ModuleOrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ModuleOrderServiceImpl implements ModuleOrderService {

    @Autowired
    private ModuleOrderMapper ModuleOrderMapper;

    @Transactional
    @Override
    public void add(ModuleOrder ModuleOrder) {
        BaseBeanHelper.insert(ModuleOrder);
        ModuleOrderMapper.insert(ModuleOrder);
    }

    @Override
    public void delete(String id) {
    ModuleOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ModuleOrder ModuleOrder) {
        BaseBeanHelper.edit(ModuleOrder);
        Example example = new Example(ModuleOrder.class);
        example.createCriteria().andEqualTo("id", ModuleOrder.getId());
        ModuleOrderMapper.updateByExampleSelective(ModuleOrder, example);
    }

    @Override
    public PageInfo<ModuleOrder> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ModuleOrderMapper.selectAll());
    }

    @Override
    public ModuleOrder getById(String id) {
        Example example = new Example(ModuleOrder.class);
        example.createCriteria().andEqualTo("id", id);
        return ModuleOrderMapper.selectOneByExample(example);
    }
}
