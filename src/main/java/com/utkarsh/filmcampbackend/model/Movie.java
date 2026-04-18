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
    private boolean adult;
    private int budget;
    private int revenue;
    private Genre[] genres;
    private String tagline;
    private int runtime;
    public void completePosterPathUrl(){
        this.setPoster_path("https://image.tmdb.org/t/p/w600_and_h900_face"+this.poster_path);
    }
    public void completeBackdropPathUrl(){
        this.setBackdrop_path("https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"+this.backdrop_path);
    }
}
