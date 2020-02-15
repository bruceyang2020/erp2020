package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Factory;
import cn.edu.hdu.clan.service.sys.FactoryService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Factory")
public class FactoryController extends BaseController {

    @Autowired
    private FactoryService FactoryService;
    @RequestMapping("add")
    public String add(@RequestBody Factory Factory) {
        FactoryService.add(Factory);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        FactoryService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Factory Factory){

        FactoryService.update(Factory);
        return success();
    }

    @RequestMapping("sale")
    public String sale(@RequestBody Factory Factory){
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        FactoryService.sale(userTeam,period,Factory.getNumber());
        return success();
    }

    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(FactoryService.list(userTeam,period));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(FactoryService.getById(param.get("id")));
    }
}