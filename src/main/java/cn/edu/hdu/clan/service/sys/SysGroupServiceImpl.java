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
    private SysGroupMapper groupMapper;

    @Transactional
    @Override
    public void add(SysGroup group) {
        BaseBeanHelper.insert(group);
        groupMapper.insert(group);
    }

    @Override
    public void delete(String id) {
    groupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysGroup group) {
        BaseBeanHelper.edit(group);
        Example example = new Example(SysGroup.class);
        example.createCriteria().andEqualTo("id", group.getId());
        groupMapper.updateByExampleSelective(group, example);
    }

    @Override
    public PageInfo<SysGroup> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(groupMapper.selectAll());
    }

    @Override
    public SysGroup getById(String id) {
        Example example = new Example(SysGroup.class);
        example.createCriteria().andEqualTo("id", id);
        return groupMapper.selectOneByExample(example);
    }
}
