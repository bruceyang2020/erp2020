package cn.edu.hdu.clan.service.sys;

/**
 * @author clan
 * @function
 * @date 2018/5/27.
 */


import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.mapper.sys.SysUserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper userDao;


    public SysUser getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(SysUser record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public SysUser findByUsernamAndPassword(String username, char[] password) {
        List<SysUser> users = userDao.getByUsernamAndPassword(username, String.valueOf(password));
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        if (users.size()>1){
            return null;
        }
        return users.get(0);

    }
}