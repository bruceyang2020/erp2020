package cn.edu.hdu.clan.controller;


import cn.edu.hdu.clan.entity.sys.SysUser;
import cn.edu.hdu.clan.service.sys.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author clan
 * @function
 * @date 2018/5/27.
 */
@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/balancesheet")
    public String balancesheet() {
        return "balancesheet";
    }
    @RequestMapping("/incomesheet")
    public String incomesheet() {
        return "incomesheet";
    }
    @RequestMapping("/marketingPre")
    public String marketingPre() {
        return "marketingPre";
    }
    @RequestMapping("/ordersheet")
    public String ordersheet() {
        return "ordersheet";
    }
    @RequestMapping("/productionsheet")
    public String productionsheet() {
        return "productionsheet";
    }

    @ResponseBody
    @RequestMapping("login")
    public String login(@RequestBody Map<String, String> params) {
        Subject subject = SecurityUtils.getSubject();
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UsernamePasswordToken(params.get("username"), params.get("password")));
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
        }
        return success("登陆成功");
    }


    @ResponseBody
    @RequestMapping("register")
    public String register(@RequestBody Map<String, String> params) {
        SysUser user = new SysUser();
        user.setUsername(params.get("username"));
        user.setPassword(params.get("password"));
        userService.addUser(user);
        return success("注册成功");
    }

    @ResponseBody
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return success("退出成功");
    }
}
