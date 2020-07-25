/**
 * 文件名：ProductServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 20:50
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.ProductMapper;
import com.order.system.dao.RoleMapper;
import com.order.system.pojo.Product;
import com.order.system.pojo.Role;
import com.order.system.service.ProductService;
import com.order.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: ProductServiceImpl
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/20 20:50
 */
@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleMapper roleMapper;
    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public List<Role> listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(Integer integer) {

    }

  @Override
  public void update(Integer integer) {

  }


}
