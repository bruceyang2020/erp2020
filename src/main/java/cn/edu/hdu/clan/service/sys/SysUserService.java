package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysUser;
import com.github.pagehelper.PageInfo;

public interface SysUserService {
    void add(SysUser SysUser);

    void delete(String id);

    void update(SysUser SysUser);

    PageInfo<SysUser> list(int pageNum, int pageSize);

    SysUser getById(String id);
}
