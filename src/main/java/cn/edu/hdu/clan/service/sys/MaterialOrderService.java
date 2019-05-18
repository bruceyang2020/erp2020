package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import com.github.pagehelper.PageInfo;

public interface MaterialOrderService {
    void add(MaterialOrder MaterialOrder);

    void delete(String id);

    void update(MaterialOrder MaterialOrder);

    PageInfo<MaterialOrder> list(int pageNum, int pageSize);

    MaterialOrder getById(String id);
}
