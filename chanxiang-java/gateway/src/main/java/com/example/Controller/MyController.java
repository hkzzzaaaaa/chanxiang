package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("mytest")
    public void mytest(){
        System.out.println("已经成功执行");
    }
}
