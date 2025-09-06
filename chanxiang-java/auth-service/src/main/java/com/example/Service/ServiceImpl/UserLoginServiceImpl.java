package com.example.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.Entity.RawPassword;
import com.example.Entity.User;
import com.example.Mapper.LoginMapper;
import com.example.Service.UserLoginService;
import com.example.Util.CreateUser;
import com.example.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private RedisTemplate<String,String> MyStringRedisTemplate;
    @Autowired
    private CreateUser createUser;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final int MAX_ERROR_COUNT = 5;
    private static final int LOCK_MINUTES = 5;
    private static final int ERROR_COUNT_EXPIRE = 5;
    @Override
    public String getToken(String email, String password) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtUtil.generateToken(authentication);
        return token;
    }

    @Override
    public String getTokenByCode(String email, String code) {
        String lockKey = "chanxiang:email:lock:" + email;
        Boolean islock= MyStringRedisTemplate.hasKey(lockKey);
        if (Boolean.TRUE.equals(islock)) {
            throw new RuntimeException("验证码多次错误，账号已锁定" + LOCK_MINUTES + "分钟，请稍后再试");
        }
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user=loginMapper.selectOne(queryWrapper);
        if (user==null){
            user=createUser.Create(email);
            loginMapper.insert(user);
            return jwtUtil.generateTokenByCaptcha(email,"ROLE_reader");
        }
        else{
            String str="chanxiang:email:"+email;
            String captcha=MyStringRedisTemplate.opsForValue().get(str);
            if (captcha==null){
                throw new RuntimeException("验证码已过期，请重新获取");
            }
            if (captcha.equals(code)){
                return jwtUtil.generateTokenByCaptcha(email,"ROLE_"+user.getRole());
            }
            else{
                String errorCountKey = "chanxiang:email:error:count:" + email;
                Long currentCount = MyStringRedisTemplate.opsForValue().increment(errorCountKey);
                if (currentCount != null && currentCount == 1) {
                    MyStringRedisTemplate.expire(errorCountKey, ERROR_COUNT_EXPIRE, TimeUnit.MINUTES);
                }
                if (currentCount != null && currentCount >= MAX_ERROR_COUNT) {
                    MyStringRedisTemplate.opsForValue().set(lockKey, "1", LOCK_MINUTES, TimeUnit.MINUTES);
                    MyStringRedisTemplate.delete(errorCountKey);
                    throw new RuntimeException("验证码连续" + MAX_ERROR_COUNT + "次错误，账号已锁定" + LOCK_MINUTES + "分钟");
                } else {
                    int remaining = MAX_ERROR_COUNT - currentCount.intValue();
                    throw new RuntimeException("验证码错误，还剩" + remaining + "次机会，超过将锁定账号");
                }
            }
        }
    }
    @Transactional
    @Override
    public void updatePassword(String email, RawPassword rawPassword) {
        if (!rawPassword.getPassword().equals(rawPassword.getRawpassword())){
            throw new RuntimeException("两次输入的密码不一致，修改失败");
        }
        String lockKey = "chanxiang:email:lock:" + email;
        Boolean islock= MyStringRedisTemplate.hasKey(lockKey);
        if (Boolean.TRUE.equals(islock)) {
            throw new RuntimeException("验证码多次错误，账号已锁定" + LOCK_MINUTES + "分钟，请稍后再试");
        }
        String str="chanxiang:email:"+email;
        String captcha=MyStringRedisTemplate.opsForValue().get(str);
        if (captcha==null){
            throw new RuntimeException("验证码已过期，请重新获取");
        }
        if (captcha.equals(rawPassword.getCaptcha())){
            UpdateWrapper updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("email",email);
            String password=passwordEncoder.encode(rawPassword.getPassword());
            updateWrapper.set("password",password);
            int rows = loginMapper.update(null,updateWrapper);
            if (rows > 0) {
                return;
            }
            else{
                throw new RuntimeException("更新失败");
            }
        }
        else{
            String errorCountKey = "chanxiang:email:error:count:" + email;
            Long currentCount = MyStringRedisTemplate.opsForValue().increment(errorCountKey);
            if (currentCount != null && currentCount == 1) {
                MyStringRedisTemplate.expire(errorCountKey, ERROR_COUNT_EXPIRE, TimeUnit.MINUTES);
            }
            if (currentCount != null && currentCount >= MAX_ERROR_COUNT) {
                MyStringRedisTemplate.opsForValue().set(lockKey, "1", LOCK_MINUTES, TimeUnit.MINUTES);
                MyStringRedisTemplate.delete(errorCountKey);
                throw new RuntimeException("验证码连续" + MAX_ERROR_COUNT + "次错误，账号已锁定" + LOCK_MINUTES + "分钟");
            } else {
                int remaining = MAX_ERROR_COUNT - currentCount.intValue();
                throw new RuntimeException("验证码错误，还剩" + remaining + "次机会，超过将锁定账号");
            }
        }
    }
}
