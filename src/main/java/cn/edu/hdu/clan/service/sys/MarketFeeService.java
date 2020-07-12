package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MarketFeeService {
   String add(MarketFee MarketFee);

    void adds(List<MarketFee>  marketFees);

    String deleteByPeriod(String userTeam,Integer period,String marketId);

    void deleteByTeamCount(String userTeam);

    void update(MarketFee MarketFee);

    PageInfo<MarketFee> list(int pageNum, int pageSize);

    MarketFee getById(String id);

    List<MarketFee> list(String userTeam ,int period);

    List<MarketFee> listFinish(String userTeam ,int period);

    void copyDataToNextPeriod(String userTeam, int period, int nextPeriod);

    List<MarketFee> listByperiod(String userTeam ,int period,String marketId);
}
