package com.example.Controller;

import com.example.Entity.*;
import com.example.Service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userservice")
public class InformationController {
    @Autowired
    InformationService informationService;
    @GetMapping("/GetInformation")
    public Result<User> GetInformation(@RequestHeader(value = "Authorization") String token){
        User user=informationService.getInformation(token);
        return Result.success(user);
    }
    @PutMapping("/UpdateBaseInformation")
    public Result UpdateBaseInformation(@RequestHeader(value = "Authorization") String token, @RequestBody BaseInformation baseInformation){
        informationService.updateBaseInformation(token,baseInformation);
        return Result.success();
    }
    @PutMapping("/UpdateStyleInformation")
    public Result UpdateStyleInformation(@RequestHeader(value = "Authorization") String token, @RequestBody StyleInformation styleInformation){
        informationService.updateStyleInformation(token,styleInformation);
        return Result.success();
    }
    @GetMapping("/GetCaptcha")
    public Result GetCaptcha(@RequestHeader(value = "Authorization") String token){
        informationService.getCaptcha(token);
        return Result.success();
    }
    @PutMapping("/UpdatePassword")
    public Result UpdatePassword(@RequestHeader(value = "Authorization") String token, @RequestBody RawPassword rawPassword){
        informationService.updatePassword(token,rawPassword);
        return Result.success();
    }
}
