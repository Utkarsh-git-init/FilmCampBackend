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
                movie.completePosterPathUrl();
                movie.completeBackdropPathUrl();
            });
            return response.getResults().subList(0,Math.min(16,response.getResults().size()));
        }
        else
            return null;
    }

    public Movie getMovieById(int id) {
        Movie movie=tmdbClient.get()
                .uri("/movie/"+id)
                .retrieve()
                .body(Movie.class);
        if(movie==null)
            throw new Error("TMDB movie details access error");
        movie.completeBackdropPathUrl();
        movie.completePosterPathUrl();
        System.out.println("moviebyid");
        return movie;
    }

    public List<Movie> searchMovies(String query) {
        TmdbMovieApiResponse response=tmdbClient.get()
                .uri("/search/movie?query="+query)
                .retrieve()
                .body(TmdbMovieApiResponse.class);
        assert response != null;
        List<Movie> list=response.getResults();
        for(Movie movie:list){
            movie.completePosterPathUrl();
            movie.completePosterPathUrl();
        }
        return list;
    }

    public List<Movie> getTopRatedMovies() {
        TmdbMovieApiResponse response=tmdbClient.get()
                .uri("/movie/top_rated")
                .retrieve()
                .body(TmdbMovieApiResponse.class);
        List<Movie> movieList=response.getResults();
        movieList=movieList.subList(0,Math.min(16,movieList.size()));
        movieList.forEach(movie -> {
            movie.completePosterPathUrl();
            movie.completeBackdropPathUrl();
        });
        return movieList;
    }
}
