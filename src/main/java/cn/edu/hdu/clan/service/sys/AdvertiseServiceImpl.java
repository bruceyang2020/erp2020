package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Advertise;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AdvertiseMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    private AdvertiseMapper AdvertiseMapper;

    @Transactional
    @Override
    public void add(Advertise Advertise) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //补充相关字段的取值
        Advertise.setTeamCount(userTeam);
        Advertise.setGroupId("1000");
        Advertise.setPeriod(period);


        //删除当前长贷记录
        Example example = new Example(Advertise.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", Advertise.getTeamCount());
        criteria.andEqualTo("period", Advertise.getPeriod());
        criteria.andEqualTo("marketId", Advertise.getMarketId());
        criteria.andEqualTo("productId", Advertise.getProductId());
        List<Advertise> oldRow = AdvertiseMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            AdvertiseMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(Advertise);
        AdvertiseMapper.insert(Advertise);

    }

    @Override
    public void addList(List<Advertise> Advertises) {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //删除当前期间的所有广告费
        Example example = new Example(Advertise.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        criteria.andEqualTo("period", period);
        List<Advertise> oldRow = AdvertiseMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            AdvertiseMapper.deleteByExample(example);
        }

        for(int i=0;i< Advertises.size();i++)
        {
            //补充相关字段的取值
            Advertises.get(i).setTeamCount(userTeam);
            Advertises.get(i).setGroupId("1000");
            Advertises.get(i).setPeriod(period);


            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.insert(Advertises.get(i));
            AdvertiseMapper.insert(Advertises.get(i));


        }

    }

    @Override
    public void delete(String id) {
    AdvertiseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Advertise Advertise) {
        BaseBeanHelper.edit(Advertise);
        Example example = new Example(Advertise.class);
        example.createCriteria().andEqualTo("id", Advertise.getId());
        AdvertiseMapper.updateByExampleSelective(Advertise, example);
    }

    @Override
    public PageInfo<Advertise> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(AdvertiseMapper.selectAll());
    }

    @Override
    public Advertise getById(String id) {
        Example example = new Example(Advertise.class);
        example.createCriteria().andEqualTo("id", id);
        return AdvertiseMapper.selectOneByExample(example);
    }
}
