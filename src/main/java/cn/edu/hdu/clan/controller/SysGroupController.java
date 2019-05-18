package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SysGroup;
import cn.edu.hdu.clan.service.sys.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("SysGroup")
public class SysGroupController extends BaseController {

    @Autowired
    private SysGroupService SysGroupService;
    @RequestMapping("add")
    public String add(@RequestBody SysGroup SysGroup) {
        SysGroupService.add(SysGroup);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        SysGroupService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody SysGroup SysGroup){
        SysGroupService.update(SysGroup);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(SysGroupService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(SysGroupService.getById(param.get("id")));
    }
}