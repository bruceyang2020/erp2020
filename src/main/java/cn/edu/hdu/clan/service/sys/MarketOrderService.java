package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MarketOrder;
import com.github.pagehelper.PageInfo;

public interface MarketOrderService {
    void add(MarketOrder MarketOrder);

    void delete(String id);

    void update(MarketOrder MarketOrder);

    PageInfo<MarketOrder> list(int pageNum, int pageSize);

    MarketOrder getById(String id);
}
