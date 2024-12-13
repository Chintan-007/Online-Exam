package com.onlineexam.online_exam_module.controller;

import com.onlineexam.online_exam_module.model.User;
import com.onlineexam.online_exam_module.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String testEndpoint() {
        return "UserController is working!";
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
    	System.out.println("Received User: " + user);
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
    	return userService.getUsers();
    }
    
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
    	System.out.println("Got Email: "+email);
        return userService.getUserByEmail(email).orElse(null);
    }
}
