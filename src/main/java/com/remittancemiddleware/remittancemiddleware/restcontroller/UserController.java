package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface UserController {
    CustomResponse getUserById(@PathVariable int userId);

    CustomResponse createUser(@RequestBody Map<String, String> creationDetails);

    CustomResponse login(@RequestBody Map<String, String> loginDetails);
}
