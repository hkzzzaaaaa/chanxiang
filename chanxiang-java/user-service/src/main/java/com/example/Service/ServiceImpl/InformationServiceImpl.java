package com.example.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.Entity.BaseInformation;
import com.example.Entity.User;
import com.example.Mapper.InformationMapper;
import com.example.Service.InformationService;
import com.example.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    InformationMapper informationMapper;
    @Override
    public User getInformation(String token) {
        String email= jwtUtil.getUsernameFromToken(token);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user=informationMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    @Transactional
    public void updateBaseInformation(String token, BaseInformation baseInformation) {
        String email= jwtUtil.getUsernameFromToken(token);
        String username= baseInformation.getUsername();
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=informationMapper.selectOne(queryWrapper);
        if (user!=null&&!user.getEmail().equals(email)){
            throw new RuntimeException("用户名已经存在，请重新输入一个用户名");
        }
        else{
            UpdateWrapper updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("email",email);
            updateWrapper.set("username",username);
            updateWrapper.set("age",baseInformation.getAge());
            updateWrapper.set("gender",baseInformation.getGender());
            updateWrapper.set("signial",baseInformation.getSignial());
            int rows = informationMapper.update(null,updateWrapper);
            if (rows > 0) {
                return;
            }
            else{
                throw new RuntimeException("更新失败");
            }
        }
    }
}
