package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.service.sys.ResearchFeeService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ResearchFee")
public class ResearchFeeController extends BaseController {

    @Autowired
    private ResearchFeeService ResearchFeeService;
    @RequestMapping(value="add",produces = "application/json;charset=utf-8")
    public String add(@RequestBody ResearchFee ResearchFee) {

        return success( ResearchFeeService.add(ResearchFee));
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ResearchFeeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ResearchFee ResearchFee){
        ResearchFeeService.update(ResearchFee);
        return success();
    }

    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(ResearchFeeService.list(userTeam,period));
    }

    /**
     * Y 列表研究完成的产品列表
     * @return
     */
    @RequestMapping(value = "listFinish",produces = "application/json;charset=utf-8")
    public String listFinish() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(ResearchFeeService.listFinish(userTeam,period));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ResearchFeeService.getById(param.get("id")));
    }
    @RequestMapping(value="deleteByPeriod",produces = "application/json;charset=utf-8")
    public String sale(@RequestBody ResearchFee ResearchFee){
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        return success( ResearchFeeService.deleteByPeriod(userTeam,period,ResearchFee.getProductId()));
    }
}