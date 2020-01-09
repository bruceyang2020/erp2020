package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.SysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper SysUserMapper;

    @Transactional
    @Override
    public void add(SysUser SysUser) {
        BaseBeanHelper.insert(SysUser);
        SysUserMapper.insert(SysUser);
    }

    @Override
    public void delete(String id) {
    SysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysUser SysUser) {
        BaseBeanHelper.edit(SysUser);
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("id", SysUser.getId());
        SysUserMapper.updateByExampleSelective(SysUser, example);
    }

    @Override
    public PageInfo<SysUser> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(SysUserMapper.selectAll());
    }

    @Override
    public SysUser getById(String id) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("id", id);
        return SysUserMapper.selectOneByExample(example);
    }
}
