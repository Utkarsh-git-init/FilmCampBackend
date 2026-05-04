package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<MovieEntity, Integer> {
}
