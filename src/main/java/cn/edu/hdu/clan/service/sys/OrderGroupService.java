package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import com.github.pagehelper.PageInfo;

public interface OrderGroupService {
    void add(OrderGroup OrderGroup);

    void delete(String id);

    void update(OrderGroup OrderGroup);

    PageInfo<OrderGroup> list(int pageNum, int pageSize);

    OrderGroup getById(String id);
}
