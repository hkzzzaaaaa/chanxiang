package com.example.Service;

public interface UserLoginService {
    String getToken(String email, String password);

    String getTokenByCode(String email, String code);
}
