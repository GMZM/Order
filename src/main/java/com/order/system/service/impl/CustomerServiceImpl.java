/**
 * 文件名：CustomerServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 17:15
 * 描述：
 */

package com.order.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.order.system.dao.CustomerMapper;
import com.order.system.pojo.Customer;
import com.order.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: CustomerServiceImpl
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 17:15
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> listAll() {
        return customerMapper.listAll();
    }

    @Override
    public List listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }

    @Override
    public Customer listByName(String name) {
        List<Customer> list = customerMapper.listByName(name);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Customer foreLogin(Customer customer) {
        List<Customer> customers = customerMapper.findByNameAndPassword(customer.getName(), customer.getPassword());
        return customers.size() > 0 ? customers.get(0) : null;
    }
}
