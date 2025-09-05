package com.example.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("User")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userid;
    @Size(min = 1,max = 15,message = "用户名长度必须在1~15个字符之间")
    private String username;
    @Email
    private String email;
    private String password;
    private Integer age;
    private String signial;
    private String favourite;
    @TableField("word_count")
    private String wordCount;
    @TableField("gender_favourite")
    private String genderFavourite;
    private String gender;
    private String role;
    private BigDecimal balance;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;

}
