package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Period;
import com.github.pagehelper.PageInfo;

public interface PeriodService {
    void add(Period Period);

    void delete(String id);

    void update(Period Period);

    PageInfo<Period> list(int pageNum, int pageSize);

    Period getById(String id);
}
