package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Bom;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.BomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class BomServiceImpl implements BomService {

    @Autowired
    private BomMapper BomMapper;

    @Transactional
    @Override
    public void add(Bom Bom) {
        BaseBeanHelper.insert(Bom);
        BomMapper.insert(Bom);
    }

    @Override
    public void delete(String id) {
    BomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Bom Bom) {
        BaseBeanHelper.edit(Bom);
        Example example = new Example(Bom.class);
        example.createCriteria().andEqualTo("id", Bom.getId());
        BomMapper.updateByExampleSelective(Bom, example);
    }

    @Override
    public PageInfo<Bom> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(BomMapper.selectAll());
    }

    @Override
    public Bom getById(String id) {
        Example example = new Example(Bom.class);
        example.createCriteria().andEqualTo("id", id);
        return BomMapper.selectOneByExample(example);
    }
}
