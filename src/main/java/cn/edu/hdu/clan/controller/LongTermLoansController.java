package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.LongTermLoans;
import cn.edu.hdu.clan.service.sys.LongTermLoansService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("LongTermLoans")
public class LongTermLoansController extends BaseController {

    @Autowired
    private LongTermLoansService LongTermLoansService;
    @RequestMapping("add")
    public String add(@RequestBody LongTermLoans LongTermLoans) {

        LongTermLoansService.add(LongTermLoans);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        LongTermLoansService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody LongTermLoans LongTermLoans){

        LongTermLoansService.update(LongTermLoans);
        return success();
    }

    @RequestMapping("list_de")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(LongTermLoansService.list(param.get("pageNum"), param.get("pageSize")));
    }
    @RequestMapping("list")
    public String list() {
        return success(LongTermLoansService.list());
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(LongTermLoansService.getById(param.get("id")));
    }

    @RequestMapping("listbyuserandperiod")
    public String getByUserIdAndPeriod() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        return success(LongTermLoansService.getByUserTeamIdAndPeriod(userTeam));
    }
}