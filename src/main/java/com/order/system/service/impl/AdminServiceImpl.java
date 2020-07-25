/**
 * 文件名：AdminServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/21 12:12
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.AdminMapper;
import com.order.system.pojo.Admin;
import com.order.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: AdminServiceImpl
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/21 12:12
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> listAll() {
        System.out.println("111111111");
        List<Admin> list = adminMapper.listAll();
        System.out.println("222222222");
        return list;
    }

    @Override
    public List<Admin> listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Admin admin) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }


    @Override
    public Admin findByName(String name) {
        System.out.println("----------------"+name);

        Admin admin = adminMapper.findByName(name);
        System.out.println(admin.getPhone());
        return admin;
    }
}
