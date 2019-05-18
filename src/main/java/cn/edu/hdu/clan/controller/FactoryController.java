package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.service.sys.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Factory")
public class FactoryController extends BaseController {

    @Autowired
    private FactoryService FactoryService;
    @RequestMapping("add")
    public String add(@RequestBody Factory Factory) {
        FactoryService.add(Factory);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        FactoryService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Factory Factory){
        FactoryService.update(Factory);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(FactoryService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(FactoryService.getById(param.get("id")));
    }
}