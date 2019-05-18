package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ShortTermLoan;
import cn.edu.hdu.clan.service.sys.ShortTermLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ShortTermLoan")
public class ShortTermLoanController extends BaseController {

    @Autowired
    private ShortTermLoanService ShortTermLoanService;
    @RequestMapping("add")
    public String add(@RequestBody ShortTermLoan ShortTermLoan) {
        ShortTermLoanService.add(ShortTermLoan);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ShortTermLoanService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ShortTermLoan ShortTermLoan){
        ShortTermLoanService.update(ShortTermLoan);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ShortTermLoanService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ShortTermLoanService.getById(param.get("id")));
    }
}