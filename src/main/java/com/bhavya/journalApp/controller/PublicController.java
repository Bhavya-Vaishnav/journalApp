package com.bhavya.journalApp.controller;

import com.bhavya.journalApp.entity.User;
import com.bhavya.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "Journal App is running!";
    }

    @PostMapping("/create-user")
    public boolean createNewUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return true;
    }
}
