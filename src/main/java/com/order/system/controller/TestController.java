/**
 * 文件名：TestController
 * 作者：liuzeming
 * 时间：2020/7/5 9:59
 * 描述：
 */

package com.order.system.controller;

import com.order.system.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Package: com.order.system.controller
 * @ClassName: TestController
 * @Author: liuzeming
 * @Description: ${description}
 * @Date: 2020/7/5 9:59
 */
@Controller
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ResponseBody
    public void test(@RequestBody Role role) {
        System.out.println("befor---------------"+role.toString());
        SetOperations setOperations = redisTemplate.opsForSet();
        redisTemplate.opsForValue().set("role", role);
        System.out.println(redisTemplate.opsForValue().get("role"));
    }
}
