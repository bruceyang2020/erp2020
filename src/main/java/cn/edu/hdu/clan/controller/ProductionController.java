package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Production;
import cn.edu.hdu.clan.service.sys.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Production")
public class ProductionController extends BaseController {

    @Autowired
    private ProductionService ProductionService;
    @RequestMapping("add")
    public String add(@RequestBody Production Production) {
        ProductionService.add(Production);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ProductionService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Production Production){
        ProductionService.update(Production);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ProductionService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ProductionService.getById(param.get("id")));
    }
}