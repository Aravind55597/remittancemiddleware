package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.User;

import java.util.Map;


public interface UserService {
    public User findById(int theId);
    public User save(Map<String,String> theUser);
    public User login(Map<String,String> loginDetails);
}
