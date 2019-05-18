package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.AccountCode;
import cn.edu.hdu.clan.service.sys.AccountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("AccountCode")
public class AccountCodeController extends BaseController {

    @Autowired
    private AccountCodeService AccountCodeService;
    @RequestMapping("add")
    public String add(@RequestBody AccountCode AccountCode) {
        AccountCodeService.add(AccountCode);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        AccountCodeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody AccountCode AccountCode){
        AccountCodeService.update(AccountCode);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(AccountCodeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(AccountCodeService.getById(param.get("id")));
    }
}