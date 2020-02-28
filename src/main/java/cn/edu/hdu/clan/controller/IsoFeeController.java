package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import cn.edu.hdu.clan.service.sys.IsoFeeService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("IsoFee")
public class IsoFeeController extends BaseController {

    @Autowired
    private IsoFeeService IsoFeeService;
    @RequestMapping("add")
    public String add(@RequestBody IsoFee IsoFee) {
        IsoFeeService.add(IsoFee);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        IsoFeeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody IsoFee IsoFee){
        IsoFeeService.update(IsoFee);
        return success();
    }

    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(IsoFeeService.list(userTeam,period));
    }
    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(IsoFeeService.getById(param.get("id")));
    }
}