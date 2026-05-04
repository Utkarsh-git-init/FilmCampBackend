package com.utkarsh.filmcampbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;
    private String username;
    private String password;
}
