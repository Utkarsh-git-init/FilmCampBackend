package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserMovieInteractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMovieInteractionsRepo extends JpaRepository<UserMovieInteractions,Integer> {

    Optional<UserMovieInteractions> findByUserAndMovie(UserModel user, MovieEntity movie);
}
