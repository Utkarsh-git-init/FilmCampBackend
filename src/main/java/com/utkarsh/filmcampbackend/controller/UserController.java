package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user){
        return userService.verify(user);
    }

    @GetMapping("/isauthenticated")
    public ResponseEntity<String> isAuthorised(){
        System.out.println("HEllo");
        return ResponseEntity.accepted().body("User is Authenticated");
    }
}
