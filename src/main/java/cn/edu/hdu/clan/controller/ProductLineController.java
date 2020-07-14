package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.service.sys.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ProductLine")
public class ProductLineController extends BaseController {

    @Autowired
    private ProductLineService ProductLineService;
    @RequestMapping("add")
    public String add(@RequestBody ProductLine ProductLine) {
        ProductLineService.add(ProductLine);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ProductLineService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ProductLine ProductLine){
        ProductLineService.update(ProductLine);
        return success();
    }

    @RequestMapping("build")
    public String build(@RequestBody ProductLine ProductLine){
        ProductLineService.build(ProductLine);
        return success();
    }

    @RequestMapping(value="inputToProduce",produces = "application/json;charset=utf-8")
    public String inputToProduce(@RequestBody ProductLine ProductLine){

        return success(ProductLineService.inputToProduce(ProductLine));
    }

    @RequestMapping("switching")
    public String switching(@RequestBody ProductLine ProductLine){
        ProductLineService.switching(ProductLine);
        return success();
    }



    @RequestMapping("sale")
    public String sale(@RequestBody ProductLine ProductLine){
        ProductLineService.sale(ProductLine);
        return success();
    }


    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list() {
        return success(ProductLineService.list());
    }



    @RequestMapping(value = "listDetail",produces = "application/json;charset=utf-8")
    public String listDetail(@RequestBody ProductLine ProductLine) {
        return success(ProductLineService.listDetail(ProductLine));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ProductLineService.getById(param.get("id")));
    }
}