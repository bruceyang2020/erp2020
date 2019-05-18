package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Bom;
import cn.edu.hdu.clan.service.sys.BomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Bom")
public class BomController extends BaseController {

    @Autowired
    private BomService BomService;
    @RequestMapping("add")
    public String add(@RequestBody Bom Bom) {
        BomService.add(Bom);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        BomService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Bom Bom){
        BomService.update(Bom);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(BomService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(BomService.getById(param.get("id")));
    }
}