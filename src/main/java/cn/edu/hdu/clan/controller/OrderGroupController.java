package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.service.sys.OrderGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("OrderGroup")
public class OrderGroupController extends BaseController {

    @Autowired
    private OrderGroupService OrderGroupService;
    @RequestMapping("add")
    public String add(@RequestBody OrderGroup OrderGroup) {
        OrderGroupService.add(OrderGroup);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        OrderGroupService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody OrderGroup OrderGroup){
        OrderGroupService.update(OrderGroup);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(OrderGroupService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(OrderGroupService.getById(param.get("id")));
    }
}