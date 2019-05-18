package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.service.sys.AccountingVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("AccountingVoucher")
public class AccountingVoucherController extends BaseController {

    @Autowired
    private AccountingVoucherService AccountingVoucherService;
    @RequestMapping("add")
    public String add(@RequestBody AccountingVoucher AccountingVoucher) {
        AccountingVoucherService.add(AccountingVoucher);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        AccountingVoucherService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody AccountingVoucher AccountingVoucher){
        AccountingVoucherService.update(AccountingVoucher);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(AccountingVoucherService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(AccountingVoucherService.getById(param.get("id")));
    }
}