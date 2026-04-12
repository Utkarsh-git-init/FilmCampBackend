package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.model.Movie;
import com.utkarsh.filmcampbackend.model.TmdbMovieApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieService {
    RestClient tmdbClient;
    public MovieService(RestClient tmdbClient){
        this.tmdbClient=tmdbClient;
    }
    public List<Movie> getTrendingMovies() {
        TmdbMovieApiResponse response=tmdbClient.get()
                .uri("/trending/movie/day")
                .retrieve()
                .body(TmdbMovieApiResponse.class);
        if(response!=null){
            response.getResults().forEach(movie ->{
                movie.setPoster_path("https://image.tmdb.org/t/p/w600_and_h900_face"+movie.getPoster_path());
                movie.setBackdrop_path("https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"+movie.getBackdrop_path());
            });
            return response.getResults();
        }
        else
            return null;
    }
}
