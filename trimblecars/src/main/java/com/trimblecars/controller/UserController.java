package com.trimblecars.controller;

import com.trimblecars.model.User;
import com.trimblecars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
         return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userService.getUserById(id).orElseThrow();
    }
}
