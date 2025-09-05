package com.example.Service;

import com.example.Entity.BaseInformation;
import com.example.Entity.User;

public interface InformationService {

    User getInformation(String token);

    void updateBaseInformation(String token, BaseInformation baseInformation);
}
