package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MarketFeeService {
    void add(MarketFee MarketFee);

    void deleteByPeriod(String userTeam,Integer period,String marketId);

    void update(MarketFee MarketFee);

    PageInfo<MarketFee> list(int pageNum, int pageSize);

    MarketFee getById(String id);

    List<MarketFee> list(String userTeam ,int period);
}
