package com.example.Feign;

import com.example.Entity.RawPassword;
import com.example.Entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface AuthFeign {
    @PostMapping("/userlogin/GetCaptcha")
    Result GetCaptcha(@RequestParam("email") String email);
    @PutMapping("/userlogin/UpdatePassword")
    Result UpdatePassword(@RequestParam("email") String email, @RequestBody RawPassword rawPassword);
}
