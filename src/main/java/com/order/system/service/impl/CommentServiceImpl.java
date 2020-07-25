/**
 * 文件名：CommentServiceImpl
 * 作者：liuzeming
 * 时间：2020/6/20 22:44
 * 描述：
 */

package com.order.system.service.impl;

import com.order.system.dao.CommentMapper;
import com.order.system.pojo.Comment;
import com.order.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.order.system.service.impl
 * @ClassName: CommentServiceImpl
 * @Author: liuzeming
 * @Description: ${description}  
 * @Date: 2020/6/20 22:44
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> listAll() {
        return commentMapper.listAll();
    }

    @Override
    public List<Comment> listOne(Integer integer) {
        return null;
    }

    @Override
    public void save(Comment comment) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Integer integer) {

    }


}
