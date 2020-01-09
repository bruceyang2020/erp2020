package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.MarketOrder;
import cn.edu.hdu.clan.service.sys.MarketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("MarketOrder")
public class MarketOrderController extends BaseController {

    @Autowired
    private MarketOrderService MarketOrderService;
    @RequestMapping("add")
    public String add(@RequestBody MarketOrder MarketOrder) {
        MarketOrderService.add(MarketOrder);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        MarketOrderService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody MarketOrder MarketOrder){
        MarketOrderService.update(MarketOrder);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(MarketOrderService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(MarketOrderService.getById(param.get("id")));
    }
}