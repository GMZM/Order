/**
 * 文件名：ProductController
 * 作者：liuzeming
 * 时间：2020/6/20 20:47
 * 描述：
 */

package com.order.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.pojo.Product;
import com.order.system.service.ProductService;
import com.order.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Package: com.order.system.controller
 * @ClassName: ProductController
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/20 20:47
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping("/list")
    public String listAll(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> list = productService.listAll();
        int total = (int)new PageInfo<>(list).getTotal();
        model.addAttribute("list",list);
        model.addAttribute("total",total);
        return "/productCategory/product-list";
    }
}
