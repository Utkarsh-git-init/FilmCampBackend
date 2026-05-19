package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.dto.CreditsDTO;
import com.utkarsh.filmcampbackend.dto.MovieDTO;
import com.utkarsh.filmcampbackend.dto.MoviePersonnelDTO;
import com.utkarsh.filmcampbackend.dto.TmdbMovieApiResponseDTO;
import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.repository.ReviewRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MovieService {
    private final ReviewRepo reviewRepo;
    RestClient tmdbClient;
    public MovieService(RestClient tmdbClient, ReviewRepo reviewRepo){
        this.tmdbClient=tmdbClient;
        this.reviewRepo = reviewRepo;
    }
    public List<MovieDTO> getTrendingMovies() {
        TmdbMovieApiResponseDTO response=tmdbClient.get()
                .uri("/trending/movie/day")
                .retrieve()
                .body(TmdbMovieApiResponseDTO.class);
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

    public MovieDTO getMovieById(int id) {
        MovieDTO movie=tmdbClient.get()
                .uri("/movie/"+id)
                .retrieve()
                .body(MovieDTO.class);
        if(movie==null)
            throw new Error("TMDB movie details access error");
        movie.completeBackdropPathUrl();
        movie.completePosterPathUrl();
        System.out.println("moviebyid");
        return movie;
    }

    public CreditsDTO getMovieCredits(int id) {
        CreditsDTO credits=tmdbClient.get()
                .uri("/movie/"+id+"/credits")
                .retrieve()
                .body(CreditsDTO.class);
        credits.getCast().forEach(MoviePersonnelDTO::completeProfilePath);
        credits.getCrew().forEach(MoviePersonnelDTO::completeProfilePath);
        System.out.println("creditsbyid");
        return credits;
    }

    public TmdbMovieApiResponseDTO searchMovies(String query,int page) {
        TmdbMovieApiResponseDTO response=tmdbClient.get()
                .uri("/search/movie?query="+query+"&page="+page)
                .retrieve()
                .body(TmdbMovieApiResponseDTO.class);
        assert response != null;
        response.completeImagePaths();
        return response;
    }

    public List<MovieDTO> getTopRatedMovies() {
        TmdbMovieApiResponseDTO response=tmdbClient.get()
                .uri("/movie/top_rated")
                .retrieve()
                .body(TmdbMovieApiResponseDTO.class);
        List<MovieDTO> movieList=response.getResults();
        movieList=movieList.subList(0,Math.min(16,movieList.size()));
        movieList.forEach(movie -> {
            movie.completePosterPathUrl();
            movie.completeBackdropPathUrl();
        });
        return movieList;
    }

    public List<MovieEntity> getPopularMoviesOnFilmCamp() {
        return reviewRepo.findMoviesWithMostReviews(PageRequest.of(0,16));
    }
}
