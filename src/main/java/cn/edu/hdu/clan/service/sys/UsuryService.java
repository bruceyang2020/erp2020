package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.entity.sys.Usury;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UsuryService {
    void add(Usury Usury);

    void delete(String id);

    void update(Usury Usury);

    PageInfo<Usury> list(int pageNum, int pageSize);

    Usury getById(String id);
    List<Usury> list();
    List<Usury> getByUserIdAndPeriod(String userTeam);

    void voucherMakerOfInterest(String userTeam,int period);
}
