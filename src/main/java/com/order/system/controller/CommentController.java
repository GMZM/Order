/**
 * 文件名：CommentController
 * 作者：liuzeming
 * 时间：2020/6/20 22:39
 * 描述：
 */

package com.order.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.system.pojo.Comment;
import com.order.system.service.CommentService;
import com.order.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Package: com.order.system.controller
 * @ClassName: CommentController
 * @Author: liuzeming
 * @Description: 评论管理
 * @Date: 2020/6/20 22:39
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Comment> list = commentService.listAll();
        int total = (int)new PageInfo<>(list).getTotal();
        model.addAttribute("list",list);
        model.addAttribute("totle",total);
        return "/comment/comment";
    }
}
