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
        delByUserIdAndPeriod(sandtableManual);

        sandtableManual.setId(UUIDHelper.getUUID());
        sandtableManual.setCreateTime(new Date());
        sandtableManual.setEditTime(new Date());
        sandtableManual.setCreateUser("yang");
        sandtableManual.setEditUser("yang");
        SandtableManualMapper.insert(sandtableManual);
    }

    public void delByUserIdAndPeriod(SandtableManual sandtableManual) {

        //删除本期数据，并将上期的数据复制到本期。

        String userId = sandtableManual.getUserId();
        int period = sandtableManual.getPeriod();
        Example example = new Example(SandtableManual.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("period", period);
        SandtableManualMapper.deleteByExample(example);



    }


    public void reloadByUserIdAndPeriod(SandtableManual sandtableManual) {

        //先删除
        delByUserIdAndPeriod(sandtableManual);

        String userId = sandtableManual.getUserId();
        int period = sandtableManual.getPeriod();
        if(period > 0)
        {
            SandtableManual myRow =   findByUserIdAndPeriod(userId,period-1);  //找到上期的数据。
            myRow.setPeriod(period);
            add(myRow);
        }else {
            String myData = "3|3|3|4|null|null|null|null|null|null|null|null|p1|null|null|null|null|null|null|null|null|p1|null|null|null|null|null|null|null|null|p1|null|null|p1|null|null|null|null|null|null|3R1|null|null|null|null|null|null|null|null|null|null|null|2|null|null|null|null|null|20|20|null|null|null|null|null|null|null|null|null|null|null|20|null|null|15|null|null|null|null|null|null|null|null|null|null|null|3p1|null|null|null|null|null|null|null|null|null|null|null|null|null|null|null|-|null|null|null|null|-|null|null|null|null|null|null|null|null|-|null|null|null|-|null|null|null|";
            SandtableManual myDefaultRow = new SandtableManual();
            myDefaultRow.setPeriod(period);
            myDefaultRow.setUserId(userId);
            myDefaultRow.setUserData(myData);
            add(myDefaultRow);

        }




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
