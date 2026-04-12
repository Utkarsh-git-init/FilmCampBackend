package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.model.Movie;
import com.utkarsh.filmcampbackend.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    MovieService movieService;
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }
    @GetMapping("/trending")
    public List<Movie> getTrendingMovies(){
        return movieService.getTrendingMovies();
    }
}
