package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ProduclineType;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.ProduclineTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class ProduclineTypeServiceImpl implements ProduclineTypeService {

    @Autowired
    private ProduclineTypeMapper ProduclineTypeMapper;

    @Transactional
    @Override
    public void add(ProduclineType ProduclineType) {
        BaseBeanHelper.insert(ProduclineType);
        ProduclineTypeMapper.insert(ProduclineType);
    }

    @Override
    public void delete(String id) {
    ProduclineTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProduclineType ProduclineType) {
        BaseBeanHelper.edit(ProduclineType);
        Example example = new Example(ProduclineType.class);
        example.createCriteria().andEqualTo("id", ProduclineType.getId());
        ProduclineTypeMapper.updateByExampleSelective(ProduclineType, example);
    }

    @Override
    public PageInfo<ProduclineType> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(ProduclineTypeMapper.selectAll());
    }

    @Override
    public ProduclineType getById(String id) {
        Example example = new Example(ProduclineType.class);
        example.createCriteria().andEqualTo("id", id);
        return ProduclineTypeMapper.selectOneByExample(example);
    }
}
