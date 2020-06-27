package cn.edu.hdu.clan.mapper.sys;

import cn.edu.hdu.clan.entity.PageData;
import cn.edu.hdu.clan.entity.sys.OrderGroup;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderGroupMapper extends Mapper<OrderGroup> {

    @Select("SELECT product_id  as productId,market_id as marketId,period,sum(amount) as myamount ,sum(price_total) as mymoney,cast(sum(price_total)/sum(amount) as DECIMAL(9,1))  as myPrice FROM order_group where group_id =  #{group_id}  group by product_id,market_id,period ORDER BY period,market_id,product_id")
    List<PageData> listAllAvgPrice(PageData pd);
}