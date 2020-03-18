package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.OrderGroup;
import cn.edu.hdu.clan.service.sys.OrderGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("OrderGroup")
public class OrderGroupController extends BaseController {

    @Autowired
    private OrderGroupService OrderGroupService;
    @RequestMapping("add")
    public String add(@RequestBody OrderGroup OrderGroup) {
        OrderGroupService.add(OrderGroup);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        OrderGroupService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody OrderGroup OrderGroup){
        OrderGroupService.update(OrderGroup);
        return success();
    }

    /**
     * 列表显示备选订单。
     * @param param
     * @return
     */
    @RequestMapping(value = "list",produces = "application/json;charset=utf-8")
    public String list(@RequestBody Map<String,String> param) {
        List<OrderGroup> myList = OrderGroupService.list(param.get("productId"));
        List<Map<String,String>> datas = new ArrayList<Map<String, String>>();


        return success(OrderGroupService.list(param.get("productId")));
    }

    /**
     * Y 生成用于图表显示全部订单的数量状图
     * @param param
     * @return
     */
    @RequestMapping(value = "listAllAmount",produces = "application/json;charset=utf-8")
    public String listAllAmount(@RequestBody Map<String,String> param) {
         List<OrderGroup> myList = OrderGroupService.listAll();
         Map<String,Object> dataMap = new HashMap<String,Object>();
        // 数组大小
        int size = 30;
        // 定义数组
        double[] myAmountListP1 = new double[size];
        double[] myAmountListP2 = new double[size];
        double[] myAmountListP3 = new double[size];
        double[] myAmountListP4 = new double[size];
        // 计算所有元素的总和
        double total = 0;
        for (int j = 0; j < size; j++) {
            myAmountListP1[j] = 0;
            myAmountListP2[j] = 0;
            myAmountListP3[j] = 0;
            myAmountListP4[j] = 0;
        }

         for(int i=0;i< myList.size();i++)
         {
              String myMarketId = myList.get(i).getMarketId();
              double  myAmount = myList.get(i).getAmount().doubleValue();
              String myProductid = myList.get(i).getProductId();
              int  myPeriod  = myList.get(i).getPeriod();
              switch (myMarketId)
              {
                  case "本地":
                      switch (myProductid)
                      {   case "P1":myAmountListP1[myPeriod/4] = myAmountListP1[myPeriod/4] +myAmount; break;
                          case "P2":myAmountListP2[myPeriod/4] = myAmountListP2[myPeriod/4] +myAmount; break;
                          case "P3":myAmountListP3[myPeriod/4] = myAmountListP3[myPeriod/4] +myAmount; break;
                          case "P4":myAmountListP4[myPeriod/4] = myAmountListP4[myPeriod/4] +myAmount; break;
                      }
                      break;
                  case "区域":
                      switch (myProductid)
                      {   case "P1":myAmountListP1[myPeriod/4+6] = myAmountListP1[myPeriod/4+6] +myAmount; break;
                          case "P2":myAmountListP2[myPeriod/4+6] = myAmountListP2[myPeriod/4+6] +myAmount; break;
                          case "P3":myAmountListP3[myPeriod/4+6] = myAmountListP3[myPeriod/4+6] +myAmount; break;
                          case "P4":myAmountListP4[myPeriod/4+6] = myAmountListP4[myPeriod/4+6] +myAmount; break;
                      }
                      break;
                  case "国内":
                      switch (myProductid)
                      {   case "P1":myAmountListP1[myPeriod/4+12] = myAmountListP1[myPeriod/4+12] +myAmount; break;
                          case "P2":myAmountListP2[myPeriod/4+12] = myAmountListP2[myPeriod/4+12] +myAmount; break;
                          case "P3":myAmountListP3[myPeriod/4+12] = myAmountListP3[myPeriod/4+12] +myAmount; break;
                          case "P4":myAmountListP4[myPeriod/4+12] = myAmountListP4[myPeriod/4+12] +myAmount; break;
                      }
                      break;
                  case "亚洲":
                      switch (myProductid)
                      {   case "P1":myAmountListP1[myPeriod/4+18] = myAmountListP1[myPeriod/4+18] +myAmount; break;
                          case "P2":myAmountListP2[myPeriod/4+18] = myAmountListP2[myPeriod/4+18] +myAmount; break;
                          case "P3":myAmountListP3[myPeriod/4+18] = myAmountListP3[myPeriod/4+18] +myAmount; break;
                          case "P4":myAmountListP4[myPeriod/4+18] = myAmountListP4[myPeriod/4+18] +myAmount; break;
                      }
                      break;
                  case "国际":
                      switch (myProductid)
                      {   case "P1":myAmountListP1[myPeriod/4+24] = myAmountListP1[myPeriod/4+24] +myAmount; break;
                          case "P2":myAmountListP2[myPeriod/4+24] = myAmountListP2[myPeriod/4+24] +myAmount; break;
                          case "P3":myAmountListP3[myPeriod/4+24] = myAmountListP3[myPeriod/4+24] +myAmount; break;
                          case "P4":myAmountListP4[myPeriod/4+24] = myAmountListP4[myPeriod/4+24] +myAmount; break;
                      }
                      break;

              }


         }

        dataMap.put("P1",myAmountListP1);
        dataMap.put("P2",myAmountListP2);
        dataMap.put("P3",myAmountListP3);
        dataMap.put("P4",myAmountListP4);



        return success(dataMap);
    }

    /**
     * Y 生成用于图表显示全部订单的价格状图
     * @param param
     * @return
     */
    @RequestMapping(value = "listAllPriceTotal",produces = "application/json;charset=utf-8")
    public String listAllPriceTotal(@RequestBody Map<String,String> param) {
        List<OrderGroup> myList = OrderGroupService.listAll();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        // 数组大小
        int size = 30;
        // 定义数组
        double[] myAmountListP1 = new double[size];
        double[] myAmountListP2 = new double[size];
        double[] myAmountListP3 = new double[size];
        double[] myAmountListP4 = new double[size];
        // 计算所有元素的总和
        double total = 0;
        for (int j = 0; j < size; j++) {
            myAmountListP1[j] = 0;
            myAmountListP2[j] = 0;
            myAmountListP3[j] = 0;
            myAmountListP4[j] = 0;
        }

        for(int i=0;i< myList.size();i++)
        {
            String myMarketId = myList.get(i).getMarketId();
            double  myAmount = myList.get(i).getPriceTotal().doubleValue();
            String myProductid = myList.get(i).getProductId();
            int  myPeriod  = myList.get(i).getPeriod();
            switch (myMarketId)
            {
                case "本地":
                    switch (myProductid)
                    {   case "P1":myAmountListP1[myPeriod/4] = myAmountListP1[myPeriod/4] +myAmount; break;
                        case "P2":myAmountListP2[myPeriod/4] = myAmountListP2[myPeriod/4] +myAmount; break;
                        case "P3":myAmountListP3[myPeriod/4] = myAmountListP3[myPeriod/4] +myAmount; break;
                        case "P4":myAmountListP4[myPeriod/4] = myAmountListP4[myPeriod/4] +myAmount; break;
                    }
                    break;
                case "区域":
                    switch (myProductid)
                    {   case "P1":myAmountListP1[myPeriod/4+6] = myAmountListP1[myPeriod/4+6] +myAmount; break;
                        case "P2":myAmountListP2[myPeriod/4+6] = myAmountListP2[myPeriod/4+6] +myAmount; break;
                        case "P3":myAmountListP3[myPeriod/4+6] = myAmountListP3[myPeriod/4+6] +myAmount; break;
                        case "P4":myAmountListP4[myPeriod/4+6] = myAmountListP4[myPeriod/4+6] +myAmount; break;
                    }
                    break;
                case "国内":
                    switch (myProductid)
                    {   case "P1":myAmountListP1[myPeriod/4+12] = myAmountListP1[myPeriod/4+12] +myAmount; break;
                        case "P2":myAmountListP2[myPeriod/4+12] = myAmountListP2[myPeriod/4+12] +myAmount; break;
                        case "P3":myAmountListP3[myPeriod/4+12] = myAmountListP3[myPeriod/4+12] +myAmount; break;
                        case "P4":myAmountListP4[myPeriod/4+12] = myAmountListP4[myPeriod/4+12] +myAmount; break;
                    }
                    break;
                case "亚洲":
                    switch (myProductid)
                    {   case "P1":myAmountListP1[myPeriod/4+18] = myAmountListP1[myPeriod/4+18] +myAmount; break;
                        case "P2":myAmountListP2[myPeriod/4+18] = myAmountListP2[myPeriod/4+18] +myAmount; break;
                        case "P3":myAmountListP3[myPeriod/4+18] = myAmountListP3[myPeriod/4+18] +myAmount; break;
                        case "P4":myAmountListP4[myPeriod/4+18] = myAmountListP4[myPeriod/4+18] +myAmount; break;
                    }
                    break;
                case "国际":
                    switch (myProductid)
                    {   case "P1":myAmountListP1[myPeriod/4+24] = myAmountListP1[myPeriod/4+24] +myAmount; break;
                        case "P2":myAmountListP2[myPeriod/4+24] = myAmountListP2[myPeriod/4+24] +myAmount; break;
                        case "P3":myAmountListP3[myPeriod/4+24] = myAmountListP3[myPeriod/4+24] +myAmount; break;
                        case "P4":myAmountListP4[myPeriod/4+24] = myAmountListP4[myPeriod/4+24] +myAmount; break;
                    }
                    break;

            }


        }

        dataMap.put("P1",myAmountListP1);
        dataMap.put("P2",myAmountListP2);
        dataMap.put("P3",myAmountListP3);
        dataMap.put("P4",myAmountListP4);



        return success(dataMap);
    }




    @RequestMapping(value = "getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(OrderGroupService.getById(param.get("id")));
    }

    /**
     * Y 检查当前的产品订单是否可以选择。
     * @param param
     * @return
     */
    @RequestMapping(value = "checkOrderRight")
    public String checkOrderRight(@RequestBody Map<String,String> param) {
        return success(OrderGroupService.checkOrderRight(param.get("orderId")));
    }


}