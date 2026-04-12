package com.utkarsh.filmcampbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @GetMapping("/")
    public String greet(HttpServletRequest httpServletRequest){
        return System.currentTimeMillis()+"Hello World"+httpServletRequest.getSession().getId();
    }
    @GetMapping("/healthcheck")
    public ResponseEntity<?> isUp(){
        return ResponseEntity.ok().body("is up");
    }
}
