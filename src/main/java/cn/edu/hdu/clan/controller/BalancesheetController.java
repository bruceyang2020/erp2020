package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Balancesheet;
import cn.edu.hdu.clan.service.sys.BalancesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Balancesheet")
public class BalancesheetController extends BaseController {

    @Autowired
    private BalancesheetService BalancesheetService;
    @RequestMapping("add")
    public String add(@RequestBody Balancesheet Balancesheet) {
        BalancesheetService.add(Balancesheet);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        BalancesheetService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Balancesheet Balancesheet){
        BalancesheetService.update(Balancesheet);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(BalancesheetService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(BalancesheetService.getById(param.get("id")));
    }
}