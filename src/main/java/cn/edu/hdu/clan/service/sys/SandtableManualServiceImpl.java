package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.SandtableManual;
import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.helper.UUIDHelper;
import cn.edu.hdu.clan.mapper.sys.SandtableManualMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SandtableManualServiceImpl implements  SandtableManualService{

    @Resource
    private SandtableManualMapper SandtableManualMapper;
    @Resource
    private SysUserService sysUserService;


    public void add(SandtableManual sandtableManual) {

        //先删除
        String userId = sandtableManual.getUserId();
        int period = sandtableManual.getPeriod();
        Example example = new Example(SandtableManual.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("period", period);
        SandtableManualMapper.deleteByExample(example);

        sandtableManual.setId(UUIDHelper.getUUID());
        sandtableManual.setCreateTime(new Date());
        sandtableManual.setEditTime(new Date());
        sandtableManual.setCreateUser("yang");
        sandtableManual.setEditUser("yang");
        SandtableManualMapper.insert(sandtableManual);
    }

    public SandtableManual findByUserIdAndPeriod(String userId,int period) {

    /*    SysUser sysUser = sysUserService.getById(userId);
        String  username = sysUser.getUsername();*/

        Example example = new Example(SandtableManual.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("period",period);
        return SandtableManualMapper.selectOneByExample(example);
    }

}
