package com.example.Controller;

import com.example.Dto.CodeBody;
import com.example.Dto.PasswordBody;
import com.example.Entity.RawPassword;
import com.example.Entity.Result;
import com.example.Service.EmailService;
import com.example.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlogin")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    EmailService emailService;
    @PostMapping("/PasswordLogin")
    public Result login(@RequestBody PasswordBody passwordBody){
        String token=userLoginService.getToken(passwordBody.getEmail(), passwordBody.getPassword());
        return Result.success(token);
    }
    @PostMapping("/GetCaptcha")
    public Result GetCaptcha(String email){
        emailService.getCaptcha(email);
        return Result.success();
    }
    @PostMapping("/CaptchaLogin")
    public Result CodeLogin(@RequestBody CodeBody codeBody){
        String token=userLoginService.getTokenByCode(codeBody.getEmail(),codeBody.getCode());
        return Result.success(token);
    }
    @PutMapping("/UpdatePassword")
    public Result UpdatePassword(String email, @RequestBody RawPassword rawPassword){
        userLoginService.updatePassword(email,rawPassword);
        return Result.success();
    }
}
