/**
 * 文件名：CustomerController
 * 作者：liuzeming
 * 时间：2020/6/20 17:14
 * 描述：
 */

package com.order.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.pojo.Customer;
import com.order.system.service.CustomerService;
import com.order.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Package: com.order.system.controller
 * @ClassName: CustomerController
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 17:14
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String listAll(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());//分页查询
        List<Customer> list = customerService.listAll();
        int total = (int) new PageInfo<>(list).getTotal();
     //   page.setTotal(total);
        model.addAttribute("list",list);
        model.addAttribute("totals",total);
        return "/cstpage/cst-list";
    }

    @RequestMapping("/listByName")
    public String listByName(@RequestParam("name")String name,Model model) {
        Customer customer = customerService.listByName(name);
        model.addAttribute(customer);
        return "/cstpage/cst-list";
    }
}
