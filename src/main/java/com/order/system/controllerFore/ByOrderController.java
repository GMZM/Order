/**
 * 文件名：ByOrderController
 * 作者：liuzeming
 * 时间：2020/6/26 17:57
 * 描述：
 */

package com.order.system.controllerFore;

import com.order.system.pojo.Customer;
import com.order.system.pojo.Order;
import com.order.system.pojo.OrderItem;
import com.order.system.pojo.Product;
import com.order.system.service.OrderItemService;
import com.order.system.service.OrderService;
import com.order.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.order.system.controllerFore
 * @ClassName: ByOrderController
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/26 17:57
 */
@RequestMapping("fore")
@Controller
public class ByOrderController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("buyNow")
    public String buyNow(Model model,@RequestParam("id") Integer id){
        Product product = productService.listOne(id).get(0);
        return "forePage/foreBuy";
    }

    /**
     * 下单的操作，计算订单的价格
     * 选择购物车里边复选框的值，String【】接收一下
     * 算出来总价格和数量
     *
     * @param model
     * @param oiid    选择购买的是什么商品，订单详情的id
     * @param session
     * @return 跳转到填写订单的页面
     */
    @RequestMapping("foreBuy")
    public String orderUI(Model model, String[] oiid, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("cst");
        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItem> orderItems1 = (List<OrderItem>) session.getAttribute("orderItems");
        if (null == orderItems1 && orderItems1.isEmpty()) {
            orderItems1 = orderItemService.listByCustomerId(customer.getId());
        }
        BigDecimal zero = new BigDecimal("0");
        for (String s : oiid) {
            for (OrderItem orderItem : orderItems1) {
                if (s.equals(orderItem.getId().toString())) {
                    BigDecimal price = orderItem.getTotalPrice();
                    zero = zero.add(price);
                    orderItems.add(orderItem);
                }
            }
        }
        //将订单项放在session中，方便下单的时候获取
        session.setAttribute("orderItems", orderItems);
        session.setAttribute("total", zero);
        session.setAttribute("number", orderItems.size());
        model.addAttribute("total", zero);
        model.addAttribute("number", orderItems.size());
        model.addAttribute("orderItems", orderItems);
        return "forePage/foreBuy";
    }

    /**
     * 订单的操作，填写收获的地址
     * 先设置订单未支付
     *
     * @param session
     * @param address
     * @return，跳转到支付的页面的请求
     */
    @RequestMapping("foreCreateOrder")
    public String foreCreateOrder(HttpSession session, String address) {
        Order order = new Order();
        Customer customer = (Customer) session.getAttribute("cst");
        BigDecimal total = (BigDecimal) session.getAttribute("total");
        String code = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
        System.out.println("--------------" + code);
        order.setCstid(customer.getId());
        order.setAddress(address);
        order.setCode(code);
        order.setStatus(0);
        order.setTotalNumber((int) session.getAttribute("number"));
        order.setTotalPrice((BigDecimal) session.getAttribute("total"));

        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        //一个订单有多个订单明细，进行关联
        orderService.related(order, orderItems);
        return "redirect:forePayed?id=" + order.getId() + "&total=" + total;
    }

    /**
     * 跳转到支付的页面，更改订单的状态
     *
     * @param id
     * @param total
     * @param model
     * @return
     */
    @RequestMapping("forePayed")
    public String forePayed(@RequestParam("id") Integer id, @RequestParam("total") Float total, Model model) {
        orderService.update(id);
        model.addAttribute("total", total + 10);
        return "forePage/forePayed";
    }

    /**
     * 查看订单，三表关联查询
     * 根据用户的id去查用户的订单
     * 根据用户的订单去查用户的订单明细
     * 根据订单明细去查商品
     * 最后都封装到order中
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("checkOrder")
    public String checkOrder(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("cst");
        if (customer == null) {
            return "redirect:loginUI";
        }
        List<Order> orders = orderService.listOrderAndProduct(customer.getId());
        model.addAttribute("os", orders);
        return "forePage/foreCheckOrder";
    }
}
