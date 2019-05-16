package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.${beanName};
import cn.edu.hdu.clan.service.sys.${beanName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("${name}")
public class ${beanName}Controller extends BaseController {

    @Autowired
    private ${beanName}Service ${name}Service;
    @RequestMapping("add")
    public String add(@RequestBody ${beanName} ${name}) {
        ${name}Service.add(${name});
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ${name}Service.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ${beanName} ${name}){
        ${name}Service.update(${name});
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(${name}Service.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(${name}Service.getById(param.get("id")));
    }
}