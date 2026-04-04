package com.utkarsh.filmcampbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserModel {
    @Id
    private String username;
    private String password;
}
