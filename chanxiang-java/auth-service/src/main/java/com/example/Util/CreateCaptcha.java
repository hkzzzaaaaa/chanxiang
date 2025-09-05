package com.example.Util;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class CreateCaptcha {
    public String getCaptcha(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}