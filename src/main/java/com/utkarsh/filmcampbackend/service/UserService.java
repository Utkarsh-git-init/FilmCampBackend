package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JWTService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(4);
    public ResponseEntity<String> registerUser(UserModel user) {
        if(userRepo.findByUsernameIgnoreCase((user.getUsername()))==null){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return ResponseEntity.ok().body("Registered");
        }else{
            System.out.println("User already exists");
            return ResponseEntity.status(409).body("User already exists");
        }
    }

    public ResponseEntity<String> verify(UserModel user) {
        try {
            Authentication authentication=authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            String token=jwtService.generate(user.getUsername());
            return ResponseEntity.ok()
                    .body(token);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("LOGIN FAILED");
        }

    }
}
