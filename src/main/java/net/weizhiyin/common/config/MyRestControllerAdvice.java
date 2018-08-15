package net.weizhiyin.common.config;

import net.weizhiyin.base.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyRestControllerAdvice{
    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        return "err";
    }
}