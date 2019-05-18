package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Market;
import com.github.pagehelper.PageInfo;

public interface MarketService {
    void add(Market Market);

    void delete(String id);

    void update(Market Market);

    PageInfo<Market> list(int pageNum, int pageSize);

    Market getById(String id);
}
