package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysGroup;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SysGroupMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class SysGroupServiceImpl implements SysGroupService {

    @Autowired
    private SysGroupMapper SysGroupMapper;

    @Transactional
    @Override
    public void add(SysGroup SysGroup) {
        BaseBeanHelper.insert(SysGroup);
        SysGroupMapper.insert(SysGroup);
    }

    @Override
    public void delete(String id) {
    SysGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysGroup SysGroup) {
        BaseBeanHelper.edit(SysGroup);
        Example example = new Example(SysGroup.class);
        example.createCriteria().andEqualTo("id", SysGroup.getId());
        SysGroupMapper.updateByExampleSelective(SysGroup, example);
    }

    @Override
    public PageInfo<SysGroup> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SysGroupMapper.selectAll());
    }

    @Override
    public SysGroup getById(String id) {
        Example example = new Example(SysGroup.class);
        example.createCriteria().andEqualTo("id", id);
        return SysGroupMapper.selectOneByExample(example);
    }
}
