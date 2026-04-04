package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public void registerUser(UserModel user) {
        if(userRepo.findByUsername(user.getUsername())==null){
            userRepo.save(user);
        }else
            System.out.println("User already exists");
    }
}
