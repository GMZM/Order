package com.order.system.service;

import com.order.system.pojo.OrderItem;

import java.util.List;

/**
 * @Package: com.order.system.service
 * @ClassName: CategoryService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 21:57
 */
public interface OrderItemService extends SysService<OrderItem>{
    public List<OrderItem> listByOid(Integer id);

    List<OrderItem> listByCustomerId(Integer id);

    public void update(OrderItem orderItem);


    void updatePrice(double result, Integer id);
}
