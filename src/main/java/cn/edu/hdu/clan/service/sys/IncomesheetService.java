package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import com.github.pagehelper.PageInfo;

public interface IncomesheetService {
    void add(Incomesheet Incomesheet);

    void delete(String id);

    void update(Incomesheet Incomesheet);

    PageInfo<Incomesheet> list(int pageNum, int pageSize);

    Incomesheet getById(String id);
}
