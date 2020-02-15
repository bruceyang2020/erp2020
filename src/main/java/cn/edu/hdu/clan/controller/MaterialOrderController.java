package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.MaterialOrder;
import cn.edu.hdu.clan.service.sys.MaterialOrderService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("MaterialOrder")
public class MaterialOrderController extends BaseController {

    @Autowired
    private MaterialOrderService MaterialOrderService;
    @RequestMapping("add")
    public String add(@RequestBody MaterialOrder MaterialOrder) {
        MaterialOrderService.add(MaterialOrder);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        MaterialOrderService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody MaterialOrder MaterialOrder){
        MaterialOrderService.update(MaterialOrder);
        return success();
    }

    @RequestMapping("list_de")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(MaterialOrderService.list(param.get("pageNum"), param.get("pageSize")));
    }
    @RequestMapping("list")
    public String list() {
        return success(MaterialOrderService.list());
    }



    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MaterialOrderService.getById(param.get("id")));
    }
}