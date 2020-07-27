package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SysTeam;
import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.service.sys.AccountBalanceService;
import cn.edu.hdu.clan.service.sys.SysTeamService;
import cn.edu.hdu.clan.service.sys.SysUserService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("SysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService SysUserService;
    @Resource
    private SysTeamService sysTeamService;
    @Resource
    private AccountBalanceService accountBalanceService;

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
    public String getCurrentInfo(@RequestBody Map<String, Integer> param) {
      //Y  当前会计期间从后台数据库中去取值  String currentAp = Jurisdiction.getUserTeamintPeriod();
        String currentUser = Jurisdiction.getUserId();
        String currentTeam = Jurisdiction.getUserTeam();
        String currentEId = Jurisdiction.getUserEID();
        String currentIlabName = Jurisdiction.getUserIlabName();


        SysTeam sysTeam = sysTeamService.getById(currentTeam);
        String currentAp = sysTeam.getState().toString();
        Map map = new HashMap();
        map.put("currentAp",currentAp);
        map.put("currentUser",currentUser);
        map.put("currentTeam",currentTeam);
        map.put("currentEId",currentEId);
        map.put("currentIlabName",currentIlabName);
        return success("ok",map);
    }

    // Y 改写教育部的回传参数
    @RequestMapping("getCurrentScore")
    public String getCurrentScore() {
        //Y  当前会计期间从后台数据库中去取值  String currentAp = Jurisdiction.getUserTeamintPeriod();
        String currentUser = Jurisdiction.getUserId();
        String currentTeam = Jurisdiction.getUserTeam();
        String currentEId = Jurisdiction.getUserEID();

        SysTeam sysTeam = sysTeamService.getById(currentTeam);
        int currentAp =sysTeam.getState();
        int myScore = 0;
        //Y 定义一个初始值为0的金额变量。
        BigDecimal myMoneyTotal = BigDecimal.valueOf(0);
        myMoneyTotal = accountBalanceService.moneyAsFar(currentTeam,currentAp);



       if(myMoneyTotal.compareTo(BigDecimal.ZERO) == 1 && currentAp > 4)
       {
           myScore = (int)(Math.random()*15 + 80);
       }
        if(myMoneyTotal.compareTo(BigDecimal.ZERO) == 1 && currentAp <= 4)
        {
            myScore = (int)(Math.random()*10 + 70);
        }
        if(myMoneyTotal.compareTo(BigDecimal.ZERO) == 0 || myMoneyTotal.compareTo(BigDecimal.ZERO) == -1)
        {
            myScore = (int)(Math.random()*10 + 60);
        }

        if( currentAp > 12)
        {
            myScore = (int)(Math.random()*15 + 80);
        }




        Map map = new HashMap();
        map.put("score",myScore);
        map.put("currentEId",currentEId);
        return success("ok",map);
    }





}