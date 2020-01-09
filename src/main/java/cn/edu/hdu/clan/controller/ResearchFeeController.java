package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ResearchFee;
import cn.edu.hdu.clan.service.sys.ResearchFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ResearchFee")
public class ResearchFeeController extends BaseController {

    @Autowired
    private ResearchFeeService ResearchFeeService;
    @RequestMapping("add")
    public String add(@RequestBody ResearchFee ResearchFee) {
        ResearchFeeService.add(ResearchFee);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ResearchFeeService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ResearchFee ResearchFee){
        ResearchFeeService.update(ResearchFee);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ResearchFeeService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ResearchFeeService.getById(param.get("id")));
    }
}