package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.entity.sys.Market;
import cn.edu.hdu.clan.entity.sys.MarketFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.mapper.sys.AccountingVoucherMapper;
import cn.edu.hdu.clan.mapper.sys.MarketFeeMapper;
import cn.edu.hdu.clan.util.Jurisdiction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource
    private MarketFeeService marketFeeService;


    @Transactional
    @Override
    //H 初始化
    public void adds(List<MarketFee>  marketFees) {
        if(marketFees.size() > 0) {
            for (int i = 0; i < marketFees.size(); i++) {
                String userTeam = Jurisdiction.getUserTeam();
                int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
                marketFees.get(i).setPeriod(period);
                marketFees.get(i).setTeamCount(userTeam);
                marketFees.get(i).setGroupId("1000");
                BaseBeanHelper.insert(marketFees.get(i));
                MarketFeeMapper.insert(marketFees.get(i));
            }
        }
    }

    @Override
    // 确定研发按钮
    public String add(MarketFee MarketFee) {

        String myMsg = "OK";

        //全局变量 写入当前公司或小组ID
        String userTeam = Jurisdiction.getUserTeam();
        //每一期都有复制，取出原始的数据
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", MarketFee.getPeriod());
        criteria.andEqualTo("marketId", MarketFee.getMarketId());
        List<MarketFee> updateRow = MarketFeeMapper.selectByExample(example);
    if(updateRow.get(0).getPeriodLeft()>0&&updateRow.get(0).getTakeRight()!=1) {

      updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() - 1);

      updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//剩余期为0，则开发完成

      updateRow.get(0).setTakeRight(1);

      //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
      BaseBeanHelper.edit(updateRow.get(0));
      MarketFeeMapper.updateByPrimaryKey(updateRow.get(0));

      String marketId = MarketFee.getMarketId();

      switch (marketId) {
        case "区域":
            //自动生成市场开拓会计凭证
            accountingVoucherService.voucherMaker(userTeam, MarketFee.getPeriod(), new BigDecimal("1"), "SCKF", "区域市场开拓");
            break;

        case "国内":
            //自动生成市场开拓会计凭证
            accountingVoucherService.voucherMaker(userTeam, MarketFee.getPeriod(), new BigDecimal("1"), "SCKF", "国内市场开拓");
            break;
        case "亚洲":
            //自动生成市场开拓会计凭证
            accountingVoucherService.voucherMaker(userTeam, MarketFee.getPeriod(), new BigDecimal("1"), "SCKF", "亚洲市场开拓");
            break;

        case "国际":
            //自动生成市场开拓会计凭证
            accountingVoucherService.voucherMaker(userTeam, MarketFee.getPeriod(), new BigDecimal("1"), "SCKF", "国际市场开拓");
            break;


      }
     }
    else{
        myMsg ="False";
    }
        return  myMsg;
    }

    //H  取消按钮
    @Override
    public String deleteByPeriod(String userTeam,Integer period,String marketId) {
        String myMsg = "OK";
        //删除当前市场开发的记录
        //H 消除已经研发完成还能删退的bug
        List<MarketFee> oldRow= marketFeeService.listByperiod(userTeam,period-1,marketId);
        List<MarketFee> updateRow= marketFeeService.listByperiod(userTeam,period,marketId);
        if(oldRow.get(0).getState()==0&&updateRow.get(0).getTakeRight()==1) {
            //补充相关字段的取值
            updateRow.get(0).setPeriodLeft(updateRow.get(0).getPeriodLeft() + 1);//剩余时间回撤
            updateRow.get(0).setTakeRight(0);//执行回撤
            updateRow.get(0).setState(updateRow.get(0).getPeriodLeft() == 0 ? 1 : 0);//这期开发过了

            //补充相关字段的取值
            //提交新增记录，自动生成GUID主键及新增的createuser ,createtime
            BaseBeanHelper.edit(updateRow.get(0));
            MarketFeeMapper.updateByPrimaryKey(updateRow.get(0));
            //删除会计凭证
            accountingVoucherService.deleteByPeriodAndContent(userTeam, period, marketId+"市场开拓");

        }
        else{
             myMsg = "False";
        }
        return myMsg;

    }

    //H
    @Override
    public void deleteByTeamCount(String userTeam) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        MarketFeeMapper.deleteByExample(example);
    }


    @Override
    public void deleteByTeamCountAndPeriod(String userTeam ,int period) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        MarketFeeMapper.deleteByExample(example);

    }

    //H
    @Override
    public List<MarketFee> listByperiod(String userTeam ,int period,String marketId) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("marketId",marketId);
        return MarketFeeMapper.selectByExample(example);
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

    @Override
    public List<MarketFee> list(String userTeam ,int period) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        return MarketFeeMapper.selectByExample(example);
    }

    /**
     * Y 列表显示已经开发完成的市场
     * @param userTeam
     * @param period
     * @return
     */
    @Override
    public List<MarketFee> listFinish(String userTeam ,int period) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        criteria.andEqualTo("state", 1);
        return MarketFeeMapper.selectByExample(example);
    }

    @Override
    public void copyDataToNextPeriod(String userTeam, int period, int nextPeriod) {
        Example example = new Example(MarketFee.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teamCount", userTeam);
        criteria.andEqualTo("period", period);
        List<MarketFee> factorys = MarketFeeMapper.selectByExample(example);

        if (factorys.size() > 0) {
            for (int i = 0; i < factorys.size(); i++) {
                MarketFee myRow = factorys.get(i);
                myRow.setPeriod(nextPeriod);
                myRow.setTakeRight(0);
                BaseBeanHelper.insert(myRow);
                MarketFeeMapper.insert(myRow);

            }
        }

    }
}
