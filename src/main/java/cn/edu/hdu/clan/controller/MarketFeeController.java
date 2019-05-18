package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.MarketFee;
import cn.edu.hdu.clan.service.sys.MarketFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        MarketFeeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody MarketFee MarketFee){
        MarketFeeService.update(MarketFee);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(MarketFeeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MarketFeeService.getById(param.get("id")));
    }
}