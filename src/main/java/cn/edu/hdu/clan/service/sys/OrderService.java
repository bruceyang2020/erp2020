package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService {
    void add(Order Order);

    void delete(String id);

    void update(Order Order);

    PageInfo<Order> list(int pageNum, int pageSize);

    Order getById(String id);
}
