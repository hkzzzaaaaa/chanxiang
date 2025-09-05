package com.example.Service.ServiceImpl;

import com.example.Service.EmailService;
import com.example.Util.CreateCaptcha;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    RedisTemplate<String,String> MyStringRedisTemplate;
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CreateCaptcha createCaptcha;
    @Value("${spring.mail.username}")
    private String form;
    @Override
    public void getCaptcha(String email) {
        String str="chanxiang:email:"+email;
        if(MyStringRedisTemplate.opsForValue().get(str)!=null){
            throw new RuntimeException("验证码已发送，60秒后重试");
        }
        String captcha= createCaptcha.getCaptcha();
        MyStringRedisTemplate.opsForValue().set(str,captcha,1, TimeUnit.MINUTES);
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(email);
        message.setSubject("[你的验证码]");
        message.setText("你的验证码是：" + captcha + "，1分钟内有效，请勿泄露给他人。");
        javaMailSender.send(message);
    }
}
