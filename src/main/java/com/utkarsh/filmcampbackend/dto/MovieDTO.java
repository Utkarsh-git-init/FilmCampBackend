package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.tomcat.util.json.JSONFilter;

import java.util.Date;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("release_date")
    private Date releaseDate;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private boolean adult;
    private long budget;
    private long revenue;
    private GenreDTO[] genres;
    private String tagline;
    private int runtime;
    public void completePosterPathUrl(){
        this.setPosterPath("https://image.tmdb.org/t/p/w600_and_h900_face"+this.posterPath);
    }
    public void completeBackdropPathUrl(){
        this.setBackdropPath("https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"+this.backdropPath);
    }
}
