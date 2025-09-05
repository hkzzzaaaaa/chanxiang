package com.example.Service;

import com.example.Entity.BaseInformation;
import com.example.Entity.RawPassword;
import com.example.Entity.StyleInformation;
import com.example.Entity.User;

public interface InformationService {

    User getInformation(String token);

    void updateBaseInformation(String token, BaseInformation baseInformation);

    void updateStyleInformation(String token, StyleInformation styleInformation);

    void getCaptcha(String token);

    void updatePassword(String token, RawPassword rawPassword);
}
