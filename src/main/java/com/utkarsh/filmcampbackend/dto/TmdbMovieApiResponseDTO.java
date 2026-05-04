package com.utkarsh.filmcampbackend.dto;


import lombok.Data;

import java.util.List;

@Data
public class TmdbMovieApiResponseDTO {
    private int page;
    private List<MovieDTO> results;
    private int total_Pages;
    private int total_results;
}
