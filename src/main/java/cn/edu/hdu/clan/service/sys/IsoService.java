package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Iso;
import com.github.pagehelper.PageInfo;

public interface IsoService {
    void add(Iso Iso);

    void delete(String id);

    void update(Iso Iso);

    PageInfo<Iso> list(int pageNum, int pageSize);

    Iso getById(String id);
}
