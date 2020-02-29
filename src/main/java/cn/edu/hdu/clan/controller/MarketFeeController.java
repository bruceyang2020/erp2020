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
    @RequestMapping("add")
    public String add(@RequestBody MarketFee MarketFee) {
        MarketFeeService.add(MarketFee);
        return success();
    }

    @RequestMapping("deleteByPeriod")
    public String sale(@RequestBody MarketFee MarketFee){
        String userTeam = Jurisdiction.getUserTeam();
        int period  = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        MarketFeeService.deleteByPeriod(userTeam,period,MarketFee.getMarketId());
        return success();
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

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MarketFeeService.getById(param.get("id")));
    }



}