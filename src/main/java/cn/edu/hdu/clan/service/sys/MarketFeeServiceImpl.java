package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.MarketFeeMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.math.BigDecimal;

@Service
public class MarketFeeServiceImpl implements MarketFeeService {

    @Autowired
    private MarketFeeMapper MarketFeeMapper;

    @Resource
    private AccountingVoucherService accountingVoucherService;

    @Transactional
    @Override
    public void add(MarketFee MarketFee) {
        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //补充相关字段的取值
        MarketFee.setTeamCount(userTeam);
        MarketFee.setGroupId("1000");
        MarketFee.setPeriodLeft(MarketFee.getPeriod()+1);

        //删除当前市场开发的记录
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", MarketFee.getTeamCount());
        criteria.andEqualTo("period", MarketFee.getPeriod());
        criteria.andEqualTo("marketId", MarketFee.getMarketId());
        List<MarketFee> oldRow = MarketFeeMapper.selectByExample(example);
        if(oldRow.size() > 0)
        {
            MarketFeeMapper.deleteByExample(example);
        }

        //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
        BaseBeanHelper.insert(MarketFee);
        MarketFeeMapper.insert(MarketFee);

        String marketId = MarketFee.getGroupId();

        switch (marketId)
        {
            case "区域":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,MarketFee.getPeriod(),new BigDecimal("1"),"SCKF","区域");
                break;

            case "国内":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,MarketFee.getPeriod(),new BigDecimal("1"),"SCKF","国内");
                break;
            case "亚洲":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,MarketFee.getPeriod(),new BigDecimal("1"),"SCKF","亚洲");
                break;

            case "国际":
                //自动生成市场开拓会计凭证
                accountingVoucherService.voucherMaker(userTeam,MarketFee.getPeriod(),new BigDecimal("1"),"SCKF","国际");
                break;


        }



    }

    @Override
    public void delete(String id) {
    MarketFeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MarketFee MarketFee) {
        BaseBeanHelper.edit(MarketFee);
        Example example = new Example(MarketFee.class);
        example.createCriteria().andEqualTo("id", MarketFee.getId());
        MarketFeeMapper.updateByExampleSelective(MarketFee, example);
    }

    @Override
    public PageInfo<MarketFee> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(MarketFeeMapper.selectAll());
    }

    @Override
    public MarketFee getById(String id) {
        Example example = new Example(MarketFee.class);
        example.createCriteria().andEqualTo("id", id);
        return MarketFeeMapper.selectOneByExample(example);
    }
}
