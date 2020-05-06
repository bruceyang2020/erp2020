package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.Advertise;
import cn.edu.hdu.clan.service.sys.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;
import cn.edu.hdu.clan.util.Jurisdiction;




@RestController
@RequestMapping("Advertise")
public class AdvertiseController extends BaseController {

    @Autowired
    private AdvertiseService AdvertiseService;
    @RequestMapping("add")
    public String add(@RequestBody Advertise Advertise) {
        AdvertiseService.add(Advertise);
        return success();
    }

    @RequestMapping("addList")
    public String addList(@RequestBody List<Advertise> Advertises) {
        AdvertiseService.addList(Advertises);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        AdvertiseService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody Advertise Advertise){
        AdvertiseService.update(Advertise);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(AdvertiseService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(AdvertiseService.getById(param.get("id")));
    }

    /**
     * Y 不需要前端传参数。直接查询当前群组、当前会计期间的广告费。
     * @param
     * @return
     */
    @RequestMapping( value = "listbyteamperiod",produces = "application/json;charset=utf-8")
    public String getByUserTeamAndPeriod() {
        String userTeam = Jurisdiction.getUserTeam();
        int period = Integer.parseInt(Jurisdiction.getUserTeamintPeriod());
        return success(AdvertiseService.getByUserTeamAndPeriod(userTeam, period));
    }
}