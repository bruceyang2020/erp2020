package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import cn.edu.hdu.clan.helper.BaseBeanHelper;
import cn.edu.hdu.clan.service.sys.MarketFeeService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("MarketFee")
public class MarketFeeController extends BaseController {

    @Autowired
    private MarketFeeService MarketFeeService;
    @RequestMapping(value="add",produces = "application/json;charset=utf-8")
    public String add(@RequestBody MarketFee MarketFee) {
        return success( MarketFeeService.add(MarketFee));
    }

    @RequestMapping(value="deleteByPeriod",produces = "application/json;charset=utf-8")
    public String deleteByperiod(@RequestBody MarketFee MarketFee){
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());

        return success(MarketFeeService.deleteByPeriod(userTeam,period,MarketFee.getMarketId()));
    }


    @RequestMapping("update")
    public String update(@RequestBody MarketFee MarketFee){
        MarketFeeService.update(MarketFee);
        return success();
    }


    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(MarketFeeService.list(userTeam,period));
    }

    /**
     * Y 列表已开发完成的市场
     * @return
     */
    @RequestMapping(value = "listFinish",produces = "application/json;charset=utf-8")
    public String listFinish() {
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(MarketFeeService.listFinish(userTeam,period));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MarketFeeService.getById(param.get("id")));
    }



}