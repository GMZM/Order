/**
 * 文件名：CategoryServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 21:57
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.CategoryMapper;
import com.order.system.dao.OrderItemMapper;
import com.order.system.dao.OrderMapper;
import com.order.system.pojo.Category;
import com.order.system.pojo.Order;
import com.order.system.pojo.OrderItem;
import com.order.system.service.CategoryService;
import com.order.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 21:57
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<Order> listAll() {
        return orderMapper.listAll();
    }

    @Override
    public List<Order> listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Order order) {
        orderMapper.save(order);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {
       orderMapper.update(integer);
    }


    @Override
    public void related(Order order, List<OrderItem> orderItems) {
        save(order);
        for (OrderItem orderItem : orderItems) {
            orderItemMapper.updateOid(order.getId(),orderItem.getId());
        }
    }

    @Override
    public List<Order> listOrderAndProduct(Integer id) {
        return orderMapper.listOrderAndProduct(id);
    }
}
