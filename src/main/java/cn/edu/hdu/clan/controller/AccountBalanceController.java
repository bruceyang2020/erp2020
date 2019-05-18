package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.service.sys.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("AccountBalance")
public class AccountBalanceController extends BaseController {

    @Autowired
    private AccountBalanceService AccountBalanceService;
    @RequestMapping("add")
    public String add(@RequestBody AccountBalance AccountBalance) {
        AccountBalanceService.add(AccountBalance);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        AccountBalanceService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody AccountBalance AccountBalance){
        AccountBalanceService.update(AccountBalance);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(AccountBalanceService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(AccountBalanceService.getById(param.get("id")));
    }
}