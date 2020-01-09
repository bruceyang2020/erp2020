package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Factory;
import com.github.pagehelper.PageInfo;

public interface FactoryService {
    void add(Factory Factory);

    void delete(String id);

    void update(Factory Factory);

    PageInfo<Factory> list(int pageNum, int pageSize);

    Factory getById(String id);
}
