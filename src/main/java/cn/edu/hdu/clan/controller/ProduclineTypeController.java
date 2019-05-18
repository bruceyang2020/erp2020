package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ProduclineType;
import cn.edu.hdu.clan.service.sys.ProduclineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ProduclineType")
public class ProduclineTypeController extends BaseController {

    @Autowired
    private ProduclineTypeService ProduclineTypeService;
    @RequestMapping("add")
    public String add(@RequestBody ProduclineType ProduclineType) {
        ProduclineTypeService.add(ProduclineType);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ProduclineTypeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ProduclineType ProduclineType){
        ProduclineTypeService.update(ProduclineType);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ProduclineTypeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ProduclineTypeService.getById(param.get("id")));
    }
}