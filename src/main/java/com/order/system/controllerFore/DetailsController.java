/**
 * 文件名：DetailsController
 * 作者：liuzeming
 * 时间：2020/6/21 17:35
 * 描述：
 */

package com.order.system.controllerFore;

import com.order.system.pojo.Product;
import com.order.system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Package: com.order.system.controllerFore
 * @ClassName: DetailsController
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/6/21 17:35
 */
@Controller
@RequestMapping("fore")
public class DetailsController {
    @Autowired
    private ProductService productService;

    @RequestMapping("detailsUI")
    public String detailsUI(@RequestParam("id") Integer id, Model model) {
        List<Product> products = productService.listOne(id);
        if (products.get(0) != null && products.size() > 0) {
            model.addAttribute("product", products.get(0));
            return "/forePage/proDetail";
        }
        return null;
    }
}
