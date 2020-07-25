package com.order.system.service;

import com.order.system.pojo.Admin;
import com.order.system.pojo.Role;

/**
 * @Package: com.order.system.service
 * @ClassName: ProductService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 20:49
 */
public interface AdminService extends SysService<Admin>{
   public Admin findByName(String name);

}
