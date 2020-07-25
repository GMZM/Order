package com.order.system.dao;

import com.order.system.pojo.Customer;
import com.order.system.pojo.Product;
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
public interface ProductMapper extends SysDao<Product> {
    public List<Product> listAll();
    public List<Product> listByName(String name);
}
