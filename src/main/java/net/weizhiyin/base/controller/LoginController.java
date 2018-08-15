package net.weizhiyin.base.controller;

import net.weizhiyin.base.annotation.Login;
import net.weizhiyin.base.entity.User;
import net.weizhiyin.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
