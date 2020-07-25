package com.order.system.service;

import com.order.system.pojo.Category;
import com.order.system.pojo.Order;
import com.order.system.pojo.OrderItem;

import java.util.List;

/**
 * @Package: com.order.system.service
 * @ClassName: CategoryService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 21:57
 */
public interface OrderService extends SysService<Order>{
    void related(Order order, List<OrderItem> orderItems);

    List<Order> listOrderAndProduct(Integer id);
}
