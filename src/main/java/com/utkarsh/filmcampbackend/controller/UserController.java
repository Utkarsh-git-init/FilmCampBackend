package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.dto.UserDTO;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UserDTO> isAuthorised(@AuthenticationPrincipal UserPrincipal userPrincipal){
        System.out.println("isAuthenticated");
        return ResponseEntity.accepted()
                .body(new UserDTO(userPrincipal.getUserId(),userPrincipal.getUsername()));
    }
}
