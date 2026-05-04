package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserMovieInteractionsDTO {
    private int movieId;
    private String poster_path;
    private String title;
    private boolean watched;
    private boolean liked;
    private boolean in_watchlist;
    private float rating;
}
