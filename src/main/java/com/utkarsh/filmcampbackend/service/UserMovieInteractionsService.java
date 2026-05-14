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

import java.util.List;

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
                    newMovie.setPosterPath(request.getPoster_path());
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
        interactions.setInWatchlist(request.isInWatchlist());
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
                userMovieInteractionsDTO.setPoster_path(interactions.getMovie().getPosterPath());
                userMovieInteractionsDTO.setRating(interactions.getRating());
                userMovieInteractionsDTO.setLiked(interactions.isLiked());
                userMovieInteractionsDTO.setWatched(interactions.isWatched());
                userMovieInteractionsDTO.setInWatchlist(interactions.isInWatchlist());
                userMovieInteractionsDTO.setUpdatedAt(interactions.getUpdatedAt());
                return userMovieInteractionsDTO;
            }
        }
    }

    public List<MovieEntity> getRecentlyInteractedMovies(UserPrincipal userPrincipal) {
        UserModel user=userRepo.getReferenceById(userPrincipal.getUserId());
        List<UserMovieInteractions> rows=interactionsRepo.findTop4ByUserOrderByUpdatedAtDesc(user);
        return rows.stream()
                .map(UserMovieInteractions::getMovie)
                .toList();
    }
}
