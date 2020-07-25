/**
 * 文件名：CategoryController
 * 作者：liuzeming
 * 时间：2020/6/20 21:57
 * 描述：
 */

package com.order.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.pojo.Category;
import com.order.system.pojo.OrderItem;
import com.order.system.service.CategoryService;
import com.order.system.service.OrderItemService;
import com.order.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Package: com.order.system.controller
 * @ClassName: CategoryController
 * @Author: liuzeming
 * @Description: 订单详情
 * @Date: 2020/6/20 21:57
 */
@Controller
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @RequestMapping("/seeOrderItem")
    public String listAll(Model model, Page page, @RequestParam("oid") Integer id){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<OrderItem> list = orderItemService.listByOid(id);
        long total = new PageInfo<>(list).getTotal();
        model.addAttribute("list",list);
        model.addAttribute("totle",total);
        return "/orderOrderItem/orderItem-list";
    }
}
