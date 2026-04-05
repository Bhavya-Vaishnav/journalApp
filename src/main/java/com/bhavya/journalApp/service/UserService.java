package com.bhavya.journalApp.service;

import com.bhavya.journalApp.entity.User;
import com.bhavya.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }catch (Exception e){
            log.info("Error occur for {} :",user.getUserName(),e);
            log.debug("hello from debug");
            return;
        }
    }

    public void saveNewAdmin(User admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(Arrays.asList("ADMIN","USER"));
        userRepository.save(admin);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
