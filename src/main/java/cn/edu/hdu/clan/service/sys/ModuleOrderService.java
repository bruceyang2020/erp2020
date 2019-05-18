package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.ModuleOrder;
import com.github.pagehelper.PageInfo;

public interface ModuleOrderService {
    void add(ModuleOrder ModuleOrder);

    void delete(String id);

    void update(ModuleOrder ModuleOrder);

    PageInfo<ModuleOrder> list(int pageNum, int pageSize);

    ModuleOrder getById(String id);
}
