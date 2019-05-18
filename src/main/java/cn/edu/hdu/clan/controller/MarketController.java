package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Market;
import cn.edu.hdu.clan.service.sys.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Market")
public class MarketController extends BaseController {

    @Autowired
    private MarketService MarketService;
    @RequestMapping("add")
    public String add(@RequestBody Market Market) {
        MarketService.add(Market);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        MarketService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Market Market){
        MarketService.update(Market);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(MarketService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MarketService.getById(param.get("id")));
    }
}