package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import com.github.pagehelper.PageInfo;

public interface SysTeamService {
    void add(SysTeam SysTeam);

    void delete(String id);

    void update(SysTeam SysTeam);

    PageInfo<SysTeam> list(int pageNum, int pageSize);

    SysTeam getById(String id);

    SysTeam getByName(String name);

    void reloadData(String userTeam,int period);

    void nextPeriod(String userTeam,Integer nextPeriod);

    void priorPeriod(String userTeam,Integer priorPeriod);
}
