package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysGroup;
import com.github.pagehelper.PageInfo;

public interface SysGroupService {
    void add(SysGroup group);

    void delete(String id);

    void update(SysGroup group);

    PageInfo<SysGroup> list(int pageNum, int pageSize);

    SysGroup getById(String id);
}
