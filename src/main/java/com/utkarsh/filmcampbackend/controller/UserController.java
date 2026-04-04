package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public void registerUser(@RequestBody UserModel user){
        userService.registerUser(user);
    }

    @GetMapping("/isauthenticated")
    public ResponseEntity<String> isAuthorised(){
        System.out.println("HEllo");
        return ResponseEntity.accepted().body("User is Authenticated");
    }
}
