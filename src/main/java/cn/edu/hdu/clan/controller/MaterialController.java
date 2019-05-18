package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Material;
import cn.edu.hdu.clan.service.sys.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Material")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService MaterialService;
    @RequestMapping("add")
    public String add(@RequestBody Material Material) {
        MaterialService.add(Material);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        MaterialService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Material Material){
        MaterialService.update(Material);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(MaterialService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MaterialService.getById(param.get("id")));
    }
}