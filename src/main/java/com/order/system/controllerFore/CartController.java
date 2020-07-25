/**
 * 文件名：CartController
 * 作者：liuzeming
 * 时间：2020/6/22 7:50
 * 描述：
 */

package com.order.system.controllerFore;

import com.order.system.pojo.Customer;
import com.order.system.pojo.OrderItem;
import com.order.system.pojo.Product;
import com.order.system.service.OrderItemService;
import com.order.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Package: com.order.system.controllerFore
 * @ClassName: CartController
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/22 7:50
 */
@RequestMapping("fore")
@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查看购物车
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("foreCartUI")
    public String foreCartUI(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("cst");
        if (customer == null) {
            return "redirect:loginUI";
        }
        List<OrderItem> orderItems = orderItemService.listByCustomerId(customer.getId());
        //将购物车中的数据存放到tomcat的session里边，方便购买某个商品的时候，直接获取到购物车的集合
        session.setAttribute("orderItems",orderItems);
        model.addAttribute("ois", orderItems);
        return "/forePage/foreCart";
    }

    /**
     * 添加到购物车 缺陷商品的总价格是错的.
     * 添加到购物车的时候，数据库已经维护了这条数据
     *
     * @param id
     * @param session
     * @param number
     * @param totalPrice
     * @return
     */
    @RequestMapping("foreAddCart")
    @ResponseBody
    public String foreAddCart(@RequestParam("pid") Integer id, HttpSession session, Integer number, Float totalPrice) {
        Customer customer = (Customer) session.getAttribute("cst");
        if (customer == null) {
            return "false";
        }
        boolean flag = true;
        //获取当前用户所有的订单的明细
        List<OrderItem> orderItems = orderItemService.listByCustomerId(customer.getId());
        BigDecimal disCount = new BigDecimal("0.8");
        BigDecimal zero = new BigDecimal("0");
        double result = 0;
        labl:
        for (OrderItem orderItem : orderItems) {
            //判断要添加的商品还在购物车中，还没有生成订单，在原来的数量上+1操作
            for (Product product1 : orderItem.getProductList()) {
                if (product1.getId().equals(id)) {
                    BigDecimal price = product1.getPrice();
                    BigDecimal totalNumber = new BigDecimal(orderItem.getNumber() + number);
                    if (1 == customer.getStatus()) {
                        price = zero.add(price.multiply(totalNumber).multiply(disCount));
                    } else {
                        price = zero.add(price.multiply(totalNumber));
                    }
                    zero = price;
                    result = zero.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    orderItem.setNumber(totalNumber.intValue());
                    orderItem.setTotalPrice(zero);
                    orderItemService.update(orderItem);
                    flag = false;
                    break labl;
                }
            }
        }
        //判断如果没有商品的话，就添加商品，数据库维护数据，但是还没有下订单
        // 下过订单之后，将订单和订单明细进行关联
        if (flag) {
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setPid(id);
            orderItem1.setNumber(number);
            orderItem1.setCstid(customer.getId());
            Product product = productService.listOne(id).get(0);
            BigDecimal price = product.getPrice();
            BigDecimal totalNumber = new BigDecimal(number);
            if (1 == customer.getStatus()) {
                price = zero.add(price.multiply(totalNumber).multiply(disCount));
            } else {
                price = zero.add(price.multiply(totalNumber));
            }
            zero = price;
            result = zero.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            orderItem1.setTotalPrice(zero);
            orderItemService.save(orderItem1);
        }
        return "success";
    }
}
