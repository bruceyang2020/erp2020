package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.SandtableManual;
import cn.edu.hdu.clan.entity.sys.SandtableOrder;
import cn.edu.hdu.clan.service.sys.SandtableOrderService;
import cn.edu.hdu.clan.util.FileDownload;
import cn.edu.hdu.clan.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("SandtableOrder")
public class SandtableOrderControler extends BaseController{

    @Autowired
    private SandtableOrderService sandtableOrderService;

    /**
     * H  初始化
     * @param
     * @return
     * **/
    @RequestMapping("adds")
    public String adds(@RequestBody  Map<String,String> param) {
        sandtableOrderService.adds(param.get("groupId"));
        return success();
    }


    /**
     * H  每年初根据小组组别返回整组的订单信息
     * @param
     * @return
     * **/
    @RequestMapping(value = "checkByGroupId",produces = "application/json;charset=utf-8")
    public String checkByGroupId(@RequestBody SandtableOrder sandtableOrder) {


        return success(sandtableOrderService.listByGroupId(sandtableOrder.getGroupId(),sandtableOrder.getPeriod()));
    }



    /**
     * H  根据选择的订单修改订单的状态和对应用户的名称
     * @param
     * @return
     * **/

    @RequestMapping(value = "add",produces = "application/json;charset=utf-8")
    public String add(@RequestBody SandtableOrder SandtableOrder) {

        return success(sandtableOrderService.add(SandtableOrder));
    }

    /**
     * H  根据选择的订单修改订单的状态和对应用户的名称
     * @param
     * @return
     * **/
    @RequestMapping("del")
    public String del(@RequestBody SandtableOrder SandtableOrder) {

        return success(sandtableOrderService.del(SandtableOrder));
    }

    /**
     * H  剩余期间根据小组组别和用户名称查询已选择的订单
     * @param
     * @return
     * **/
    @RequestMapping(value = "checkByGroupIdAndUserId",produces = "application/json;charset=utf-8")
    public String checkByGroupIdAndUserId(@RequestBody SandtableOrder sandtableOrder ){
        return success(sandtableOrderService.listByGroupIdAndUserId(sandtableOrder.getGroupId(), sandtableOrder.getUserId(), sandtableOrder.getPeriod()));
    }


}
