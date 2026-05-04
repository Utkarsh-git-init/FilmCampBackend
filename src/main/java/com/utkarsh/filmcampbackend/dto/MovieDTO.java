package com.utkarsh.filmcampbackend.dto;

import lombok.Data;
import org.apache.tomcat.util.json.JSONFilter;

import java.util.Date;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private Date release_date;
    private String backdrop_path;
    private boolean adult;
    private long budget;
    private long revenue;
    private GenreDTO[] genres;
    private String tagline;
    private int runtime;
    public void completePosterPathUrl(){
        this.setPoster_path("https://image.tmdb.org/t/p/w600_and_h900_face"+this.poster_path);
    }
    public void completeBackdropPathUrl(){
        this.setBackdrop_path("https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"+this.backdrop_path);
    }
}
