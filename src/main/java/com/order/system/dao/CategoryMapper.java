package com.order.system.dao;

import com.order.system.pojo.Category;
import com.order.system.pojo.Customer;
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
public interface CategoryMapper {
    public List<Category> listAll();
    public List<Category> listByName(String name);

    Category listProductByCategoryId(Integer id);
}
