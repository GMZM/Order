/**
 * 文件名：LoginController
 * 作者：liuzeming
 * 时间：2020/6/9 8:25
 * 描述：
 */

package com.order.system.controller;

import com.order.system.pojo.Admin;
import com.order.system.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //有异常的话，登陆失败，没有异常的话登陆成功
        try {
            subject.login(token);
            Admin admin = adminService.findByName(name);
            if (admin != null) {
                return "index";
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("error", "账户名不存在");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("error", "用户名或密码错误");
            return "/login";
        }
        return "login";
    }

}
