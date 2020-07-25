/**
 * 文件名：CategoryServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 21:57
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.OrderItemMapper;
import com.order.system.pojo.OrderItem;
import com.order.system.pojo.Product;
import com.order.system.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 21:57
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderitemMapper;

    @Override
    public List<OrderItem> listAll() {
        return orderitemMapper.listAll();
    }

    @Override
    public List<OrderItem> listOne(Integer id) {
        return orderitemMapper.listOne(id);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderitemMapper.save(orderItem);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }

    @Override
    public List<OrderItem> listByOid(Integer id) {
        return orderitemMapper.listByOid(id);
    }

    @Override
    public List<OrderItem>  listByCustomerId(Integer id) {
        List<OrderItem> orderItems = orderitemMapper.listByCustomerId(id);
        return orderItems;
    }

    @Override
    public void update(OrderItem orderItem) {
        orderitemMapper.update(orderItem);
    }

    @Override
    public void updatePrice(double result, Integer id) {
        orderitemMapper.updatePrice(result,id);
    }


}
