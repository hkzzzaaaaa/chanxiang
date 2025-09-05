package com.example.Exception;

import com.example.Entity.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result handleUsernameNotFoundException(UsernameNotFoundException e) {
        e.printStackTrace();
        return Result.error("邮箱未注册");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result handleBadCredentialsException(BadCredentialsException e) {
        e.printStackTrace();
        return Result.error("认证失败：邮箱未注册或者密码错误");
    }
    @ExceptionHandler(Exception.class)
    public Result HandlerException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
