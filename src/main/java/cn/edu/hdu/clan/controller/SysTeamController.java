package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.service.sys.SysTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("SysTeam")
public class SysTeamController extends BaseController {

    @Autowired
    private SysTeamService SysTeamService;
    @RequestMapping("add")
    public String add(@RequestBody SysTeam SysTeam) {
        SysTeamService.add(SysTeam);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        SysTeamService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody SysTeam SysTeam){
        SysTeamService.update(SysTeam);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(SysTeamService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(SysTeamService.getById(param.get("id")));
    }
}