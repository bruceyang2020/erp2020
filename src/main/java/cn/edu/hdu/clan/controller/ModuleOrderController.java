package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ModuleOrder;
import cn.edu.hdu.clan.service.sys.ModuleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ModuleOrder")
public class ModuleOrderController extends BaseController {

    @Autowired
    private ModuleOrderService ModuleOrderService;
    @RequestMapping("add")
    public String add(@RequestBody ModuleOrder ModuleOrder) {
        ModuleOrderService.add(ModuleOrder);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ModuleOrderService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ModuleOrder ModuleOrder){
        ModuleOrderService.update(ModuleOrder);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ModuleOrderService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ModuleOrderService.getById(param.get("id")));
    }
}