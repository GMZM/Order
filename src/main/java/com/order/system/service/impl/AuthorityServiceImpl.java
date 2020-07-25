/**
 * 文件名：ProductServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 20:50
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.AuthorityMapper;
import com.order.system.pojo.Authority;
import com.order.system.service.AuthorityService;
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
public class AuthorityServiceImpl implements AuthorityService {
  @Autowired
  private AuthorityMapper authorityMapper;


  @Override
  public List<Authority> listAll() {
    return authorityMapper.listAll();
  }

  @Override
  public List<Authority> listOne(Integer integer) {
    return null;
  }

  @Override
  public void save(Authority authority) {

  }

  @Override
  public void delete(Integer integer) {

  }

  @Override
  public void update(Integer integer) {

  }


}
