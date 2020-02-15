package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.service.sys.OrderManagementService;
import cn.edu.hdu.clan.service.sys.OrderGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("OrderManagement")
public class OrderManagementController extends BaseController {

    @Autowired
    @Resource
    private OrderManagementService OrderManagementService;

    @Resource
    private OrderGroupService OrderGroupService;

    @RequestMapping("add")
    public String add(@RequestBody Map<String,String> param) {

       OrderGroup orderGroup =  OrderGroupService.getByOrderId(param.get("orderId"));
        OrderManagementService.add(orderGroup);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        OrderManagementService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody OrderManagement OrderManagement){
        OrderManagementService.update(OrderManagement);
        return success();
    }


    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list(@RequestBody Map<String,String> param) {
        return success(OrderManagementService.list(param.get("productId")));
    }

    //
    @RequestMapping(value = "stockout",produces = "application/json;charset=utf-8")
    public String stockout(@RequestBody Map<String,String> param) {
        OrderManagementService.stockOut(param.get("orderId"));

        return success();
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(OrderManagementService.getById(param.get("id")));
    }
}