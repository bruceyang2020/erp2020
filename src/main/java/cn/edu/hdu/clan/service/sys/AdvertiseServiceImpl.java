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
import tk.mybatis.mapper.entity.Example.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    private AdvertiseMapper AdvertiseMapper;

    @Resource
    AccountingVoucherService accountingVoucherService;

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

    /**
     * Y 广告投放
     * @param Advertises
     */
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
        BigDecimal moneyTotal=BigDecimal.valueOf(0);
        for(int i=0;i< Advertises.size();i++)
        {
            //补充相关字段的取值
            Advertises.get(i).setTeamCount(userTeam);
            Advertises.get(i).setGroupId("1000");
            Advertises.get(i).setPeriod(period);
            moneyTotal=moneyTotal.add(Advertises.get(i).getMoneyI());

            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.insert(Advertises.get(i));
            AdvertiseMapper.insert(Advertises.get(i));


        }
        accountingVoucherService.voucherMaker(userTeam,period,moneyTotal,"GGF","本期广告费合计");


    }

    @Override
    public void delete(String id) {
    AdvertiseMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void deleteByTeamCount(String userTeam) {

        //Y 用于初始化，清空广告费的全面信息
        Example example = new Example(Advertise.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount",userTeam);
        List<Advertise> oldRow1 = AdvertiseMapper.selectByExample(example);
        if(oldRow1.size() > 0)
        {
            AdvertiseMapper.deleteByExample(example);
        }

    }


    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(Advertise.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        AdvertiseMapper.deleteByExample(example);

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

    /**
     * Y  根据当前群组与会计期间获取广告投放的数据
     * @param userTeam
     * @param period
     * @return
     */
    @Override
    public List<Advertise> getByUserTeamAndPeriod(String userTeam,int period) {
        Example example = new Example(Advertise.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return AdvertiseMapper.selectByExample(example);
    }






    @Override
    public Advertise getById(String id) {
        Example example = new Example(Advertise.class);
        example.createCriteria().andEqualTo("id", id);
        return AdvertiseMapper.selectOneByExample(example);
    }
}
