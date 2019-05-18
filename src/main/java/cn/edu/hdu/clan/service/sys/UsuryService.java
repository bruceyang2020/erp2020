package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Usury;
import com.github.pagehelper.PageInfo;

public interface UsuryService {
    void add(Usury Usury);

    void delete(String id);

    void update(Usury Usury);

    PageInfo<Usury> list(int pageNum, int pageSize);

    Usury getById(String id);
}
