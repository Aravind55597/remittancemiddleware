package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.service.UserServiceImpl;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserControllerImpl(UserServiceImpl theUserServiceImpl) {
        this.userServiceImpl = theUserServiceImpl;
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable(value="userId") int userId) {

        User theUser = userServiceImpl.findById(userId);

        if (theUser == null) {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser;
    }

    @PostMapping(value="/user")
    public User createUser(@RequestBody Map<String,String> creationDetails ) {
        return userServiceImpl.save(creationDetails);
    }

    @PostMapping(value="/user/login")
    public User login(@RequestBody Map<String,String> loginDetails) {
        return userServiceImpl.login(loginDetails);
    }
}
