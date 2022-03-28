package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
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
    public CustomResponse getUserById(@PathVariable(value="userId") int userId) {

        User theUser = userServiceImpl.findById(userId);

        if (theUser == null) {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return new CustomResponse(theUser);
    }

    @PostMapping(value="/user")
    public CustomResponse createUser(@RequestBody Map<String,String> creationDetails ) {
        return new CustomResponse(userServiceImpl.save(creationDetails));
    }

    @PostMapping(value="/user/login")
    public CustomResponse login(@RequestBody Map<String,String> loginDetails) {
        return new CustomResponse(userServiceImpl.login(loginDetails));
    }
}
