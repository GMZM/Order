/**
 * 文件名：AuthorityController
 * 作者：liuzeming
 * 时间：2020/6/21 8:51
 * 描述：
 */

package com.order.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.pojo.Authority;
import com.order.system.service.AuthorityService;
import com.order.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Package: com.order.system.controller
 * @ClassName: AuthorityController
 * @Author: liuzeming
 * @Description: 权限管理
 * @Date: 2020/6/21 8:51
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @RequestMapping("/list")
    public String listAll(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Authority> list = authorityService.listAll();
        int total = (int)new PageInfo<>(list).getTotal();
        model.addAttribute("list",list);
        model.addAttribute("total",total);
        return "/AdminPage/admin-authority";
    }
}
