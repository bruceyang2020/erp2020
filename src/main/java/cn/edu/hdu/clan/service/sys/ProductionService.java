package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Production;
import com.github.pagehelper.PageInfo;

public interface ProductionService {
    void add(Production Production);

    void delete(String id);

    void update(Production Production);

    PageInfo<Production> list(int pageNum, int pageSize);

    Production getById(String id);
}
