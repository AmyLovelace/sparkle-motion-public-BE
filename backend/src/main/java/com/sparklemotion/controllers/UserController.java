package com.sparklemotion.controllers;

import com.sparklemotion.entities.User;
import com.sparklemotion.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {


    private final UserService userService;


    @PostMapping
    public Boolean create(@RequestBody User user) {
        return userService.createUserIfNotExists(user);
    }

}
