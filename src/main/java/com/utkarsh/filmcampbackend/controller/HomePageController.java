package com.utkarsh.filmcampbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @GetMapping("/")
    public String greet(HttpServletRequest httpServletRequest){
        return System.currentTimeMillis()+"Hello World"+httpServletRequest.getSession().getId();
    }
}
