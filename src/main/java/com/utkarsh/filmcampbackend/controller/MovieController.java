package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.model.Movie;
import com.utkarsh.filmcampbackend.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id){
        return movieService.getMovieById(id);
    }

    @GetMapping("/search/{query}")
    public List<Movie> searchMovies(@PathVariable String query){
        return movieService.searchMovies(query);
    }
}
