package com.order.system.dao;

import com.order.system.pojo.Customer;
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
public interface CustomerMapper {
    public List<Customer> listAll();

    public List<Customer> listByName(String name);

    public List<Customer> findByNameAndPassword(@Param("username") String name, @Param("password") String password);
}
