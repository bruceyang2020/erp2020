package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Payables;
import cn.edu.hdu.clan.service.sys.PayablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Payables")
public class PayablesController extends BaseController {

    @Autowired
    private PayablesService PayablesService;
    @RequestMapping("add")
    public String add(@RequestBody Payables Payables) {
        PayablesService.add(Payables);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        PayablesService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Payables Payables){
        PayablesService.update(Payables);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(PayablesService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(PayablesService.getById(param.get("id")));
    }
}