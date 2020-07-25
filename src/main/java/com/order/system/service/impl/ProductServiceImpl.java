/**
 * 文件名：ProductServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 20:50
 * 描述：
 */

package com.order.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.dao.ProductMapper;
import com.order.system.pojo.Product;
import com.order.system.service.ProductService;
import com.order.system.util.Page;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> listAll() {
        List<Product> list = productMapper.listAll();
        return list;
    }

    @Override
    public List<Product> listOne(Integer id) {
        List<Product> products = productMapper.listOne(id);
        return products.size() > 0 ? products : null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }


}
