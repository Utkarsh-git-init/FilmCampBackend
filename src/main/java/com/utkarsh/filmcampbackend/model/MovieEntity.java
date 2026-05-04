package com.utkarsh.filmcampbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MovieEntity {
    @Id
    private int id;
    private String poster_path;
    private String title;
}
