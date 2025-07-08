package com.example.mannegmenthoutai.controller;

import com.example.mannegmenthoutai.dto.LoginRequest;
import com.example.mannegmenthoutai.dto.LoginResponse;
import com.example.mannegmenthoutai.dto.PageRequest;
import com.example.mannegmenthoutai.dto.UserAddResponse;
import com.example.mannegmenthoutai.dto.UserPageResponse;
import com.example.mannegmenthoutai.dto.UserQueryRequest;
import com.example.mannegmenthoutai.dto.UserUpdateRequest;
import com.example.mannegmenthoutai.dto.UserUpdateResponse;
import com.example.mannegmenthoutai.model.User;
import com.example.mannegmenthoutai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }


    
    @PostMapping("/list")
    public UserPageResponse getUserList(@RequestBody PageRequest request) {
        return userService.getUserList(request);
    }
    @PutMapping("/adduser")
    public UserAddResponse addUser(@RequestBody UserUpdateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setClassId(request.getClassId());
        return userService.addUser(user);
    }

    @PutMapping("/updateUser/{userId}")
    public UserUpdateResponse updateUser(@PathVariable Integer userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }


    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

     @DeleteMapping("deleteUser/{userId}")
     public void deleteUser(@PathVariable Integer userId) {
         userService.deleteUser(userId);
     }


}
