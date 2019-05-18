package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Inv;
import com.github.pagehelper.PageInfo;

public interface InvService {
    void add(Inv Inv);

    void delete(String id);

    void update(Inv Inv);

    PageInfo<Inv> list(int pageNum, int pageSize);

    Inv getById(String id);
}
