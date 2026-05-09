package com.utkarsh.filmcampbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MovieEntity {
    @Id
    private int id;
    @Column(name = "poster_path")
    @JsonProperty("poster_path")
    private String posterPath;
    private String title;
}
