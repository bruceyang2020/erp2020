package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Iso;
import cn.edu.hdu.clan.service.sys.IsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Iso")
public class IsoController extends BaseController {

    @Autowired
    private IsoService IsoService;
    @RequestMapping("add")
    public String add(@RequestBody Iso Iso) {
        IsoService.add(Iso);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        IsoService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Iso Iso){
        IsoService.update(Iso);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(IsoService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(IsoService.getById(param.get("id")));
    }
}