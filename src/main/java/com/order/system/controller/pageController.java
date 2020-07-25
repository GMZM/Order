/**
 * 文件名：pageController
 * 作者：liuzeming
 * 时间：2020/6/9 18:35
 * 描述：
 */

package com.order.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {
    @RequestMapping("")
    public String index(){
       return "login";
   }


}
