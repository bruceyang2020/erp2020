package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Salepayment;
import cn.edu.hdu.clan.service.sys.SalepaymentService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Salepayment")
public class SalepaymentController extends BaseController {

    @Autowired
    private SalepaymentService SalepaymentService;
    @RequestMapping("add")
    public String add(@RequestBody Salepayment Salepayment) {
        SalepaymentService.add(Salepayment);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        SalepaymentService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Salepayment Salepayment){
        SalepaymentService.update(Salepayment);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(SalepaymentService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(SalepaymentService.getById(param.get("id")));
    }

    @RequestMapping("listReceivable")
    public String listRec() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(SalepaymentService.listRec(userTeam,period));
    }


    @RequestMapping("discountMoney")
    public String discountedMoney() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success();
    }

}