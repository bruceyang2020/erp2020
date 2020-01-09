package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Period;
import cn.edu.hdu.clan.service.sys.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Period")
public class PeriodController extends BaseController {

    @Autowired
    private PeriodService PeriodService;
    @RequestMapping("add")
    public String add(@RequestBody Period Period) {
        PeriodService.add(Period);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        PeriodService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Period Period){
        PeriodService.update(Period);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(PeriodService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(PeriodService.getById(param.get("id")));
    }
}