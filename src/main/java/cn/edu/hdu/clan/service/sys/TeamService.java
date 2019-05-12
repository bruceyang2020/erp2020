package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysTeam;

import java.util.List;

public interface TeamService {
    void add(SysTeam team);

    void delete(String id);

    void update(SysTeam team);

    List<SysTeam> getByGroupId(String id);
}
