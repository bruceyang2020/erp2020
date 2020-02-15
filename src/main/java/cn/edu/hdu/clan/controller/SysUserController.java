package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.service.sys.SysUserService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("SysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService SysUserService;
    @RequestMapping("add")
    public String add(@RequestBody SysUser SysUser) {
        SysUserService.add(SysUser);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        SysUserService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody SysUser SysUser){
        SysUserService.update(SysUser);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(SysUserService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(SysUserService.getById(param.get("id")));
    }


    @RequestMapping("getCurrentInfo")
    public String getCurrentInfo() {
        String currentAp = Jurisdiction.getUserTeamintPeriod();
        String currentUser = Jurisdiction.getUserId();
        String currentTeam = Jurisdiction.getUserTeam();
        Map map = new HashMap();
        map.put("currentAp",currentAp);
        map.put("currentUser",currentUser);
        map.put("currentTeam",currentTeam);
        return success("ok",map);
    }




}