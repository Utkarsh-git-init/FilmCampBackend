package com.utkarsh.filmcampbackend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private Date release_date;
    private String backdrop_path;
}
