package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Order;
import cn.edu.hdu.clan.service.sys.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService OrderService;
    @RequestMapping("add")
    public String add(@RequestBody Order Order) {
        OrderService.add(Order);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        OrderService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Order Order){
        OrderService.update(Order);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(OrderService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(OrderService.getById(param.get("id")));
    }
}