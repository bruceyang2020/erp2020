package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderManagement;
import com.github.pagehelper.PageInfo;

public interface OrderManagementService {
    void add(OrderManagement OrderManagement);

    void delete(String id);

    void update(OrderManagement OrderManagement);

    PageInfo<OrderManagement> list(int pageNum, int pageSize);

    OrderManagement getById(String id);
}
