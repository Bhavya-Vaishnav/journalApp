package com.bhavya.journalApp.controller;

import com.bhavya.journalApp.api.response.WeatherResponse;
import com.bhavya.journalApp.entity.User;
import com.bhavya.journalApp.repository.UserRepository;
import com.bhavya.journalApp.service.UserService;
import com.bhavya.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);

        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                userInDb.setRoles(user.getRoles());
            }
            userService.saveUser(userInDb);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int weather = weatherService.getWeather("Mumbai").getCurrent().getFeelslike();
        return new ResponseEntity<>("Hi "+authentication.getName()+" The weather feels like "+weather,HttpStatus.OK);
    }


}
