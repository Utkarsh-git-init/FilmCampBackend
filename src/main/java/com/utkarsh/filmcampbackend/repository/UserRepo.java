package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends JpaRepository<UserModel,String> {
    UserModel findByUsername(String username);
}
