package com.order.system.dao;


import com.order.system.pojo.Order;
import com.order.system.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
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
public interface OrderItemMapper extends SysDao<OrderItem> {
    public List<OrderItem> listByOid(Integer id);

    public List<OrderItem> listByCustomerId(Integer id);

    public void update(OrderItem orderItem);

    void updateOid(@Param("oid") Integer oid, @Param("id") Integer id);

    void updatePrice(@Param("result") double result, @Param("id") Integer id);
}
