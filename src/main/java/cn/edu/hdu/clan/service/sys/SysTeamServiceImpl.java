package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SysTeamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class SysTeamServiceImpl implements SysTeamService {

    @Autowired
    private SysTeamMapper SysTeamMapper;

    @Transactional
    @Override
    public void add(SysTeam SysTeam) {
        BaseBeanHelper.insert(SysTeam);
        SysTeamMapper.insert(SysTeam);
    }

    @Override
    public void delete(String id) {
    SysTeamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysTeam SysTeam) {
        BaseBeanHelper.edit(SysTeam);
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("id", SysTeam.getId());
        SysTeamMapper.updateByExampleSelective(SysTeam, example);
    }

    @Override
    public PageInfo<SysTeam> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SysTeamMapper.selectAll());
    }

    @Override
    public SysTeam getById(String id) {
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("id", id);
        return SysTeamMapper.selectOneByExample(example);
    }
}
