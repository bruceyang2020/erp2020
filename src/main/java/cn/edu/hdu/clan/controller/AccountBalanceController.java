package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.AccountBalance;
import cn.edu.hdu.clan.service.sys.AccountBalanceService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("AccountBalance")
public class AccountBalanceController extends BaseController {

    @Autowired
    private AccountBalanceService AccountBalanceService;
    @RequestMapping("add")
    public String add(@RequestBody AccountBalance AccountBalance) {
        AccountBalanceService.add(AccountBalance);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        AccountBalanceService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody AccountBalance AccountBalance){
        AccountBalanceService.update(AccountBalance);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(AccountBalanceService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(AccountBalanceService.getById(param.get("id")));
    }

    @RequestMapping("moneyTotal")
    public String moneyTotal() {
       String userTeam = Jurisdiction.getUserTeam();
       int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //Y 定义一个Map，用来保存：键-值对。
        Map<String,Object> dataMap = new HashMap<String,Object>();//

        //Y 定义一个初始值为0的金额变量。
        BigDecimal myMoneyTotal = BigDecimal.valueOf(0);
        myMoneyTotal = AccountBalanceService.moneyAsFar(userTeam,period);


        //测试代码,随机生成一个数字，发到前端显示
      /* int x=(int)(Math.random()*100);
        myMoneyTotal = BigDecimal.valueOf(x);*/

        dataMap.put("moneytotal",myMoneyTotal);

      return  success(dataMap);
   }
}