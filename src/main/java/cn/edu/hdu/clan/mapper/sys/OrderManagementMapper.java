package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.sys.OrderManagement;
import tk.mybatis.mapper.common.Mapper;

public interface OrderManagementMapper extends Mapper<OrderManagement> {
    public void save();
    public OrderManagement query();
}