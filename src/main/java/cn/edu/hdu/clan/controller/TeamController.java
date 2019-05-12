package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.service.sys.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("team")
public class TeamController extends BaseController {

    @Autowired
    private TeamService teamService;
    @RequestMapping("add")
    public String add(@RequestBody SysTeam team) {
        teamService.add(team);
        return success();

    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        teamService.delete(param.get("id"));
        return success();
    }
    @RequestMapping("update")
    public String update(@RequestBody SysTeam team){
        teamService.update(team);
        return success();
    }

    @RequestMapping("getByGroupId")
    public String getByGroupId(@RequestBody Map<String,String> param) {
        return  success(
                teamService.getByGroupId(param.get("id"))
        );
    }

}
