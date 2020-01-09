package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IncomesheetService {
    void add(Incomesheet Incomesheet);

    void delete(String id);

    void update(Incomesheet Incomesheet);

    PageInfo<Incomesheet> list(int pageNum, int pageSize);
    List<Incomesheet> list();

    Incomesheet getById(String id);
    Incomesheet getincomesheet(Incomesheet incomesheet);
}
