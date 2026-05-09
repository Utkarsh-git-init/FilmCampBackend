package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserMovieInteractionsDTO {
    private int movieId;
    private String poster_path;
    private String title;
    private boolean watched;
    private boolean liked;
    @JsonProperty("in_watchlist")
    private boolean inWatchlist;
    private float rating;
    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
}
