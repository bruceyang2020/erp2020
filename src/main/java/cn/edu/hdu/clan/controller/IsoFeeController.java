package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.IsoFee;
import cn.edu.hdu.clan.service.sys.IsoFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("IsoFee")
public class IsoFeeController extends BaseController {

    @Autowired
    private IsoFeeService IsoFeeService;
    @RequestMapping("add")
    public String add(@RequestBody IsoFee IsoFee) {
        IsoFeeService.add(IsoFee);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        IsoFeeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody IsoFee IsoFee){
        IsoFeeService.update(IsoFee);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(IsoFeeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(IsoFeeService.getById(param.get("id")));
    }
}