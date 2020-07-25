/**
 * 文件名：ForeLogin
 * 作者：liuzeming
 * 时间：2020/6/21 15:23
 * 描述：
 */

package com.order.system.controllerFore;

import com.order.system.pojo.Category;
import com.order.system.pojo.Customer;
import com.order.system.service.CategoryService;
import com.order.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.order.system.controllerFore
 * @ClassName: ForeLogin
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/21 15:23
 */
@Controller
@RequestMapping("fore")
public class ForeLoginController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("loginUI")
    public String loginUI() {
        return "forePage/foreLogin";
    }

    @RequestMapping("registerUI")
    public String registerUI() {
        return "forePage/foreRegister";
    }

    @RequestMapping(value = "foreLogin", method = RequestMethod.POST)
    public String foreLogin(Customer customer, HttpSession session) {
        Customer ct = customerService.foreLogin(customer);
        if (ct != null) {
            session.setAttribute("cst", ct);
            return "redirect:foreIndex";
        }
        return "redirect:foreLoginMsg";
    }

    /**
     * 返回错误信息
     *
     * @param request
     * @return
     */
    @RequestMapping("foreLoginMsg")
    public String foreLoginMsg(HttpServletRequest request) {
        request.setAttribute("msg", "true");
        return "forePage/foreLogin";
    }

    @RequestMapping("foreRegister")
    public String foreRegister() {
        return "forePage/foreRegister";
    }

    /**
     * ajax判断客户是否登陆
     *
     * @param session
     * @return
     */
    @RequestMapping("foreIsLogin")
    @ResponseBody
    public String foreIsLogin(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("cst");
        return customer == null ? "fasle" : "true";
    }

    /**
     * 动态窗口登陆验证
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("foreMtLogin")
    @ResponseBody
    public String foreMtLogin(@RequestParam("name") String username, @RequestParam("password") String password, HttpSession session) {
        Customer customer = customerService.listByName(username);
        if (customer != null && customer.getPassword().equals(password)) {
            session.setAttribute("cst", customer);
            return "true";
        }
        return "false";
    }

    /**
     * 传入4个分类，找到4个分类对应的product，在页面上进行显示
     *
     * @param model
     * @return
     */
    @RequestMapping("foreIndex")
    public String foreIndex(Model model) {
        //传入4个分类
        List<Category> categories = categoryService.listAll();
        List<Category> categories1 = new ArrayList<>();
        for (Category category : categories) {
            Category category1 = categoryService.listProductByCategoryId(category.getId());
            categories1.add(category1);
        }
        categories1.remove(5);
        categories1.remove(4);
        model.addAttribute("categories", categories1);
        return "forePage/foreIndex";
    }

    @RequestMapping("foreCstLoginOut")
    public String foreCstLoginOut(HttpSession session) {
        session.removeAttribute("cst");
        return "forePage/foreIndex";
    }
}
