package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Inv;
import cn.edu.hdu.clan.service.sys.InvService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Inv")
public class InvController extends BaseController {

    @Autowired
    private InvService InvService;
    @RequestMapping("add")
    public String add(@RequestBody Inv Inv) {
        InvService.add(Inv);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        InvService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Inv Inv){
        InvService.update(Inv);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(InvService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(InvService.getById(param.get("id")));
    }

    @RequestMapping("listInv")
    public String listInv() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(InvService.listInv(userTeam,period));
    }



}