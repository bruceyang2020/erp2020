package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Plan;
import cn.edu.hdu.clan.service.sys.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Plan")
public class PlanController extends BaseController {

    @Autowired
    private PlanService PlanService;
    @RequestMapping("add")
    public String add(@RequestBody Plan Plan) {
        PlanService.add(Plan);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        PlanService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Plan Plan){
        PlanService.update(Plan);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(PlanService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(PlanService.getById(param.get("id")));
    }
}