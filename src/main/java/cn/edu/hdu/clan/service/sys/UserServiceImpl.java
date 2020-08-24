package cn.edu.hdu.clan.service.sys;

/**
 * @author clan
 * @function
 * @date 2018/5/27.
 */


import cn.edu.hdu.clan.SystemException;
import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.helper.UUIDHelper;
import cn.edu.hdu.clan.mapper.sys.SysUserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper userMapper;


    public SysUser getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public SysUser findByUsername(String username) {
        SysUser user = new SysUser();
        user.setUsername(username);
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username",username);
        List<SysUser> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            return null;
        }
        return users.get(0);

    }

    public void addUser(SysUser record) {

        SysUser user = new SysUser();
        user.setUsername(record.getUsername());
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo(user);
        int count = userMapper.selectCountByExample(example);
        if (count != 0) {
            throw new SystemException("该用户名已存在");
        }
      //  BaseBeanHelper.insert(record);
        record.setId(UUIDHelper.getUUID());
        record.setCreateTime(new Date());
        record.setCreateUser(record.getUsername());
        record.setEditTime(new Date());
        record.setEditUser(record.getUsername());
        record.setRegistrationTime(new Date());
        userMapper.insert(record);
    }

    @Override
    public SysUser findByUsernameAndPassword(String username, char[] password) {
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(String.valueOf(password));
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo(user);
        List<SysUser> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            return null;
        }
        return users.get(0);

    }
}