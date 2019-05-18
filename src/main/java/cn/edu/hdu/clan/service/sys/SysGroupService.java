package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysGroup;
import com.github.pagehelper.PageInfo;

public interface SysGroupService {
    void add(SysGroup SysGroup);

    void delete(String id);

    void update(SysGroup SysGroup);

    PageInfo<SysGroup> list(int pageNum, int pageSize);

    SysGroup getById(String id);
}
