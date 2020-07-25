/**
 * 文件名：CategoryServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 21:57
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.CategoryMapper;
import com.order.system.pojo.Category;
import com.order.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/20 21:57
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listAll() {
        return categoryMapper.listAll();
    }

    @Override
    public List<Category> listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }


    @Override
    public Category listProductByCategoryId(Integer id) {
        return categoryMapper.listProductByCategoryId(id);
    }
}
