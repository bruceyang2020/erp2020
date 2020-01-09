package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.FactoryType;
import cn.edu.hdu.clan.service.sys.FactoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("FactoryType")
public class FactoryTypeController extends BaseController {

    @Autowired
    private FactoryTypeService FactoryTypeService;
    @RequestMapping("add")
    public String add(@RequestBody FactoryType FactoryType) {
        FactoryTypeService.add(FactoryType);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        FactoryTypeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody FactoryType FactoryType){
        FactoryTypeService.update(FactoryType);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(FactoryTypeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(FactoryTypeService.getById(param.get("id")));
    }
}