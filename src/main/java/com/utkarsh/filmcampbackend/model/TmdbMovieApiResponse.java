package com.utkarsh.filmcampbackend.model;


import lombok.Data;

import java.util.List;

@Data
public class TmdbMovieApiResponse {
    private int page;
    private List<Movie> results;
    private int total_Pages;
    private int total_results;
}
