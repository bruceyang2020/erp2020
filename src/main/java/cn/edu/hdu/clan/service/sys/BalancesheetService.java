package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.entity.sys.Incomesheet;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BalancesheetService {
    void add(Balancesheet Balancesheet);

    void delete(String id);

    void update(Balancesheet Balancesheet);

    PageInfo<Balancesheet> list(int pageNum, int pageSize);

    List<Balancesheet> list();

    Balancesheet getById(String id);

    List<Balancesheet>  getByUserIdAndPeriod(String create_user,int period);
}
