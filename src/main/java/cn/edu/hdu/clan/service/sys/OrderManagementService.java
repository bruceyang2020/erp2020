package cn.edu.hdu.clan.service.sys;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.entity.sys.Usury;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderManagementService {
    void add(OrderGroup  orderGroup);

    void delete(String id);

    void stockOut(String orderId);

    void update(OrderManagement OrderManagement);


    OrderManagement getById(String id);

    List<OrderManagement> list(String productId);

    List<OrderManagement> listCurrentPeriodOrder();

    void deleteByTeamCount(String userTeam) ;

    }
