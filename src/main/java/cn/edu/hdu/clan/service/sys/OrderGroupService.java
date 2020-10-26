package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.PageData;
import cn.edu.hdu.clan.entity.sys.OrderGroup;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface OrderGroupService {
    void add(OrderGroup OrderGroup);

    void delete(String id);

    void update(OrderGroup OrderGroup);

    List<OrderGroup> listToSandtableOrder();

    List<OrderGroup> list(String productId);

    List<OrderGroup> listByUserTeamAndPeriod();

    List<OrderGroup> listAll();

    List<PageData> listAllAvgPrice();

    OrderGroup getById(String id);

    OrderGroup getByOrderId(String orderId);

    String checkOrderRight(String orderId);

    String checkIsoRight(String orderId);
}
