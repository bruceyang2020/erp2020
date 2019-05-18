package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.LoanType;
import cn.edu.hdu.clan.service.sys.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("LoanType")
public class LoanTypeController extends BaseController {

    @Autowired
    private LoanTypeService LoanTypeService;
    @RequestMapping("add")
    public String add(@RequestBody LoanType LoanType) {
        LoanTypeService.add(LoanType);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        LoanTypeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody LoanType LoanType){
        LoanTypeService.update(LoanType);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(LoanTypeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(LoanTypeService.getById(param.get("id")));
    }
}