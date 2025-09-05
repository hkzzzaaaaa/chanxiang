package com.example.Util;

import com.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUser {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User Create(String email){
        String uuid= UUID.randomUUID().toString();
        uuid=uuid.substring(0,15);
        User user=new User();
        user.setUsername(uuid);
        user.setEmail(email);
        String password= passwordEncoder.encode("123456");
        user.setPassword(password);
        user.setAge(0);
        user.setSignial("系统原装个性签名");
        user.setFavourite("全部");
        user.setWordCount("无");
        user.setGenderFavourite("无");
        user.setGender("保密");
        return user;
    }
}
