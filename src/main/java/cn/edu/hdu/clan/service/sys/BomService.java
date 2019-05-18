package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Bom;
import com.github.pagehelper.PageInfo;

public interface BomService {
    void add(Bom Bom);

    void delete(String id);

    void update(Bom Bom);

    PageInfo<Bom> list(int pageNum, int pageSize);

    Bom getById(String id);
}
