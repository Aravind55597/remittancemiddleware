package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.User;

import java.util.Map;


public interface UserService {
    User findById(int theId);

    User save(Map<String, String> theUser);

    User login(Map<String, String> loginDetails);
}
