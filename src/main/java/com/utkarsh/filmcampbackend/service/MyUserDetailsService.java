package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user=userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("UserNotFound");
        }
        return new UserPrincipal(user);
    }
}
