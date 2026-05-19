package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.dto.ReviewRequestDTO;
import com.utkarsh.filmcampbackend.dto.ReviewResponseDTO;
import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.model.Review;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.repository.MovieRepo;
import com.utkarsh.filmcampbackend.repository.ReviewRepo;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;

    public CommentsService(ReviewRepo reviewRepo, UserRepo userRepo, MovieRepo movieRepo) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
    }

    public void postReview(ReviewRequestDTO requestDTO, UserPrincipal userPrincipal) {
        Review review=new Review();
        Review parent=null;
        if(requestDTO.getParentId()!=null){
            parent=reviewRepo.findById(requestDTO.getParentId()).orElse(null);
        }
        MovieEntity movie=movieRepo.findById(requestDTO.getMovie().getId()).orElseGet(
                ()->{
                    MovieEntity newMovie=new MovieEntity();
                    newMovie.setId(requestDTO.getMovie().getId());
                    newMovie.setTitle(requestDTO.getMovie().getTitle());
                    newMovie.setPosterPath(requestDTO.getMovie().getPosterPath());
                    return movieRepo.save(newMovie);
                }
        );
        UserModel user=userRepo.getReferenceById(userPrincipal.getUserId());
        review.setContent(requestDTO.getContent());
        review.setUser(user);
        review.setMovie(movie);
        review.setParent(parent);
        reviewRepo.save(review);
    }

    public ReviewResponseDTO convertToDTO(Review review){
        ReviewResponseDTO dto=new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setMovieId(review.getMovie().getId());
        dto.setUserId(review.getUser().getUserId());
        dto.setUsername(review.getUser().getUsername());
        dto.setContent(review.getContent());
        dto.setCreatedAt(review.getCreatedAt());
        List<ReviewResponseDTO> replies=new ArrayList<>();
        for(Review reply:review.getReplies()){
            replies.add(convertToDTO(reply));
        }
        dto.setReplies(replies);
        return dto;
    }
    public List<ReviewResponseDTO> getReviews(int movieId) {
        List<Review> reviews=reviewRepo.findAllByMovieIdAndParentIsNullOrderByCreatedAtDesc(movieId);
        List<ReviewResponseDTO> response=new ArrayList<>();
        for(Review review:reviews){
            response.add(convertToDTO(review));
        }
        return response;
    }
}
