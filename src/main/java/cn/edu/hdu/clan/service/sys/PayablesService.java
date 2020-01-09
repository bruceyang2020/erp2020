package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Payables;
import com.github.pagehelper.PageInfo;

public interface PayablesService {
    void add(Payables Payables);

    void delete(String id);

    void update(Payables Payables);

    PageInfo<Payables> list(int pageNum, int pageSize);

    Payables getById(String id);
}
