package com.order.system.dao;

import com.order.system.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.order.system.dao
 * @ClassName: AdminMapper
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/21 12:15
 */
@Repository
public interface AdminMapper extends SysDao<Admin> {
    public  Admin findByName(String name);
}
