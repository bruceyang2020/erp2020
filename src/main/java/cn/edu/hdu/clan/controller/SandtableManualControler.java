package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.hdu.clan.entity.sys.SandtableManual;
import cn.edu.hdu.clan.service.sys.SandtableManualService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("SandtableManual")
public class SandtableManualControler  extends BaseController{

    @Autowired
    private SandtableManualService sandtableManualService;
    @RequestMapping("add")
    public String add(@RequestBody SandtableManual SandtableManual) {
        sandtableManualService.add(SandtableManual);
        return success();
    }

    @ResponseBody
    @RequestMapping("listbyuserandperiod")
    public String getByUsernameAndPeriod(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        int period = Integer.parseInt(param.get("period"));
        String userData = "";

        SandtableManual sandtableManual =sandtableManualService.findByUserIdAndPeriod(userId,period);

        if(sandtableManual != null) {
            userData = sandtableManual.getUserData();
        }

        //Y 定义一个Map，用来保存：键-值对。
        Map<String,Object> dataMap = new HashMap<String,Object>();//
        dataMap.put("paneldata",userData);

        return success(dataMap);
    }
}
