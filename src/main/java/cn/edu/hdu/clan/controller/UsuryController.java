package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Usury;
import cn.edu.hdu.clan.service.sys.UsuryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Usury")
public class UsuryController extends BaseController {

    @Autowired
    private UsuryService UsuryService;
    @RequestMapping("add")
    public String add(@RequestBody Usury Usury) {
        UsuryService.add(Usury);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        UsuryService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Usury Usury){
        UsuryService.update(Usury);
        return success();
    }

    @RequestMapping("list_de")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(UsuryService.list(param.get("pageNum"), param.get("pageSize")));
    }
    @RequestMapping("list")
    public String list() {
        return success(UsuryService.list());
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(UsuryService.getById(param.get("id")));
    }
}