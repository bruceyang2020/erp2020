package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.AccountingVoucher;
import cn.edu.hdu.clan.service.sys.AccountingVoucherService;
import cn.edu.hdu.clan.util.Jurisdiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("listForExpense")
    public String moneyTotal() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        //Y 定义一个Map，用来保存：键-值对。
        Map<String,Object> dataMap = new HashMap<String,Object>();//

        //Y 定义一个初始值为0的金额变量。
        BigDecimal zj= new BigDecimal(0);
       BigDecimal sds= new BigDecimal(0);
        BigDecimal wx= new BigDecimal(0);
        BigDecimal bg= new BigDecimal(0);
        BigDecimal gg= new BigDecimal(0);
        List<BigDecimal> mylist=AccountingVoucherService.listForExpense(userTeam,period);

        wx=mylist.get(1);
        gg=mylist.get(2);
        bg=mylist.get(3);
        zj=mylist.get(4);
        sds=mylist.get(5);


        //测试代码,随机生成一个数字，发到前端显示
      /* int x=(int)(Math.random()*100);
        myMoneyTotal = BigDecimal.valueOf(x);*/

        dataMap.put("depreciation",zj);
        dataMap.put("incometax",sds);
        dataMap.put("managementcost",bg);
        dataMap.put("avdertisedcost",gg);
        dataMap.put("repaires",wx);

        return  success(dataMap);
    }
}