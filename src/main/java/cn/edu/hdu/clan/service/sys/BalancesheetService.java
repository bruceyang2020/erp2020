package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import com.github.pagehelper.PageInfo;

public interface BalancesheetService {
    void add(Balancesheet Balancesheet);

    void delete(String id);

    void update(Balancesheet Balancesheet);

    PageInfo<Balancesheet> list(int pageNum, int pageSize);

    Balancesheet getById(String id);
}
