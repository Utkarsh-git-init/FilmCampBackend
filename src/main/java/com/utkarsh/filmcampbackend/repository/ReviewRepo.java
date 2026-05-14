package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findAllByMovieId(Integer movieId);

    List<Review> findAllByMovieIdAndParentIsNull(Integer movieId);

    List<Review> findAllByMovieIdAndParentIsNullOrderByCreatedAtDesc(Integer movieId);
}
