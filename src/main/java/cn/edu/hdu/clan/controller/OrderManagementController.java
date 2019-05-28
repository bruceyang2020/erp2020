package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.OrderManagement;
import cn.edu.hdu.clan.service.sys.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("OrderManagement")
public class OrderManagementController extends BaseController {

    @Autowired
    private OrderManagementService OrderManagementService;
    @RequestMapping("add")
    public String add(@RequestBody OrderManagement OrderManagement) {
        OrderManagementService.add(OrderManagement);
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

    @RequestMapping("list_de")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(OrderManagementService.list(param.get("pageNum"), param.get("pageSize")));
    }
    @RequestMapping("list")
    public String list() {
        return success(OrderManagementService.list());
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(OrderManagementService.getById(param.get("id")));
    }
}