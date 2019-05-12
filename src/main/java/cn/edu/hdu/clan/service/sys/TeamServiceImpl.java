package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SysTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private SysTeamMapper teamMapper;

    @Transactional
    @Override
    public void add(SysTeam team) {
        BaseBeanHelper.insert(team);
        teamMapper.insert(team);
    }

    @Override
    public void delete(String id) {
        teamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysTeam team) {
        BaseBeanHelper.edit(team);
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("id", team.getId());
        teamMapper.updateByExampleSelective(team, example);
    }


    @Override
    public List<SysTeam> getByGroupId(String id) {
        Example example = new Example(SysTeam.class);
        example.createCriteria().andEqualTo("groupId", id);
        return teamMapper.selectByExample(example);
    }

}
