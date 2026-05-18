package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserMovieInteractions;
import com.utkarsh.filmcampbackend.repository.UserMovieInteractionsRepo;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLibraryService {
    private final UserRepo userRepo;
    private final UserMovieInteractionsRepo userMovieInteractionsRepo;

    public UserLibraryService(UserRepo userRepo, UserMovieInteractionsRepo userMovieInteractionsRepo) {
        this.userRepo = userRepo;
        this.userMovieInteractionsRepo = userMovieInteractionsRepo;
    }

    public List<MovieEntity> getRecentlyInteractedMovies(String username) {
        UserModel user=userRepo.findByUsername(username);
        List<UserMovieInteractions> list=userMovieInteractionsRepo.findTop4ByUserOrderByUpdatedAtDesc(user);
        return list.stream()
                .map(UserMovieInteractions::getMovie)
                .toList();
    }

    public List<MovieEntity> getWatchlist(String username){
        UserModel user=userRepo.findByUsername(username);
        List<UserMovieInteractions> list=userMovieInteractionsRepo.findAllByUserAndInWatchlistIsTrue(user);
        return list.stream()
                .map(UserMovieInteractions::getMovie)
                .toList();
    }

    public List<MovieEntity> getLiked(String username){
        UserModel user=userRepo.findByUsername(username);
        List<UserMovieInteractions> list=userMovieInteractionsRepo.findAllByUserAndLikedIsTrue(user);
        return list.stream()
                .map(UserMovieInteractions::getMovie)
                .toList();
    }
    public List<MovieEntity> getWatched(String username){
        UserModel user=userRepo.findByUsername(username);
        List<UserMovieInteractions> list=userMovieInteractionsRepo.findAllByUserAndWatchedIsTrue(user);
        return list.stream()
                .map(UserMovieInteractions::getMovie)
                .toList();
    }
}
