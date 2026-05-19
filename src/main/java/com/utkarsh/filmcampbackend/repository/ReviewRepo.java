package com.utkarsh.filmcampbackend.repository;

import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findAllByMovieId(Integer movieId);

    List<Review> findAllByMovieIdAndParentIsNull(Integer movieId);

    List<Review> findAllByMovieIdAndParentIsNullOrderByCreatedAtDesc(Integer movieId);

    @Query("""
            SELECT r.movie
            from Review r
            GROUP BY r.movie
            order by count(r) DESC
            """
    )
    List<MovieEntity> findMoviesWithMostReviews(Pageable pageable);
}
