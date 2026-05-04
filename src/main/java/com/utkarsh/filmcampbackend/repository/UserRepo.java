package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findByUsername(String username);

    UserModel findByUsernameIgnoreCase(String username);
}
