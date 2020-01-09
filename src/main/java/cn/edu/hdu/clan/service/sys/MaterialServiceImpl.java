package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Material;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MaterialMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper MaterialMapper;

    @Transactional
    @Override
    public void add(Material Material) {
        BaseBeanHelper.insert(Material);
        MaterialMapper.insert(Material);
    }

    @Override
    public void delete(String id) {
    MaterialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Material Material) {
        BaseBeanHelper.edit(Material);
        Example example = new Example(Material.class);
        example.createCriteria().andEqualTo("id", Material.getId());
        MaterialMapper.updateByExampleSelective(Material, example);
    }

    @Override
    public PageInfo<Material> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MaterialMapper.selectAll());
    }

    @Override
    public Material getById(String id) {
        Example example = new Example(Material.class);
        example.createCriteria().andEqualTo("id", id);
        return MaterialMapper.selectOneByExample(example);
    }
}
