package net.weizhiyin.base.controller;

import net.weizhiyin.base.service.UserService;
import net.weizhiyin.common.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @Login
    public ModelAndView login(){
        ModelAndView view=new ModelAndView("/index/index");
        return view;
    }
}
