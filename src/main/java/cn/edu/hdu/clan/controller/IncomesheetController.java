package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Incomesheet;
import cn.edu.hdu.clan.service.sys.IncomesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Incomesheet")
public class IncomesheetController extends BaseController {

    @Autowired
    private IncomesheetService IncomesheetService;
    @RequestMapping("add")
    public String add(@RequestBody Incomesheet Incomesheet) {
        IncomesheetService.add(Incomesheet);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        IncomesheetService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Incomesheet Incomesheet){
        IncomesheetService.update(Incomesheet);
        return success();
    }

    @RequestMapping("list-de")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(IncomesheetService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("list")
    public String list() {
        return success(IncomesheetService.list());
    }


    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(IncomesheetService.getById(param.get("id")));
    }

}