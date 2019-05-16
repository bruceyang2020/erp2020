package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysUser;

public interface UserService {
    /**
     * 根据Id获取用户
     * @param userId
     * @return
     */
    SysUser getUserById(int userId);

    /**
     * 添加用户
     * @param record
     *
     */
    void addUser(SysUser record);

    SysUser findByUsernameAndPassword(String username, char[] password);
}
