package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.dto.UserMovieInteractionsDTO;
import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserMovieInteractions;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.repository.MovieRepo;
import com.utkarsh.filmcampbackend.repository.UserMovieInteractionsRepo;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserMovieInteractionsService {

    private final UserRepo userRepo;
    MovieRepo movieRepo;
    UserMovieInteractionsRepo interactionsRepo;

    UserMovieInteractionsService(MovieRepo movieRepo, UserMovieInteractionsRepo interactionsRepo, UserRepo userRepo){
        this.movieRepo=movieRepo;
        this.interactionsRepo=interactionsRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void updateStates(UserMovieInteractionsDTO request, UserPrincipal userPrincipal) {
        MovieEntity movie=movieRepo.findById(request.getMovieId())
                .orElseGet(()->{
                    MovieEntity newMovie=new MovieEntity();
                    newMovie.setId(request.getMovieId());
                    newMovie.setTitle(request.getTitle());
                    newMovie.setPoster_path(request.getPoster_path());
                    return movieRepo.save(newMovie);
                });
        UserModel user=userRepo.getReferenceById(userPrincipal.getUserId());
        UserMovieInteractions interactions=interactionsRepo.findByUserAndMovie(user,movie)
                .orElseGet(()->{
                    UserMovieInteractions interactions1=new UserMovieInteractions();
                    interactions1.setUser(user);
                    interactions1.setMovie(movie);
                    return interactions1;
                });
        interactions.setLiked(request.isLiked());
        interactions.setWatched(request.isWatched());
        interactions.setRating(request.getRating());
        interactions.setIn_watchlist(request.isIn_watchlist());
        interactionsRepo.save(interactions);
    }

    public UserMovieInteractionsDTO getInteractionState(int movieId, UserPrincipal userPrincipal) {
        MovieEntity movie=movieRepo.findById(movieId)
                .orElse(null);
        if(movie==null)
            return null;
        else{
            UserModel user=userRepo.getReferenceById(userPrincipal.getUserId());
            UserMovieInteractions interactions=interactionsRepo.findByUserAndMovie(user,movie)
                    .orElse(null);
            if(interactions==null)
                return null;
            else{
                UserMovieInteractionsDTO userMovieInteractionsDTO=new UserMovieInteractionsDTO();
                userMovieInteractionsDTO.setMovieId(interactions.getMovie().getId());
                userMovieInteractionsDTO.setTitle(interactions.getMovie().getTitle());
                userMovieInteractionsDTO.setPoster_path(interactions.getMovie().getPoster_path());
                userMovieInteractionsDTO.setRating(interactions.getRating());
                userMovieInteractionsDTO.setLiked(interactions.isLiked());
                userMovieInteractionsDTO.setWatched(interactions.isWatched());
                userMovieInteractionsDTO.setIn_watchlist(interactions.isIn_watchlist());
                return userMovieInteractionsDTO;
            }
        }
    }
}
