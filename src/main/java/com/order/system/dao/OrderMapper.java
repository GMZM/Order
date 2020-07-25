package com.order.system.dao;


import com.order.system.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.order.system.dao
 * @ClassName: CustomerMapper
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 17:33
 */
@Repository
public interface OrderMapper extends SysDao<Order> {

   public List<Order> listOrderAndProduct(Integer id);
}
