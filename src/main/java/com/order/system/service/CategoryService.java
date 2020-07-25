package com.order.system.service;

import com.order.system.pojo.Category;

import java.util.List;

/**
 * @Package: com.order.system.service
 * @ClassName: CategoryService
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/20 21:57
 */
public interface CategoryService extends SysService<Category>{
    Category listProductByCategoryId(Integer id);
}
