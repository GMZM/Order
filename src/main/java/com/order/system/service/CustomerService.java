package com.order.system.service;

import com.order.system.pojo.Customer;

import java.util.List;

/**
 * @Package: com.order.system.service
 * @ClassName: CustomerService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 17:15
 */
public interface CustomerService  extends SysService{
    public Customer listByName(String name);
    public Customer foreLogin(Customer customer);
}
