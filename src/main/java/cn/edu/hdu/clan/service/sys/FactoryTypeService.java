package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.FactoryType;
import com.github.pagehelper.PageInfo;

public interface FactoryTypeService {
    void add(FactoryType FactoryType);

    void delete(String id);

    void update(FactoryType FactoryType);

    PageInfo<FactoryType> list(int pageNum, int pageSize);

    FactoryType getById(String id);
}
