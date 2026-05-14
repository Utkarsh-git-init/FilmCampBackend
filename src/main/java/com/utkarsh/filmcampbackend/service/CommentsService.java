package com.utkarsh.filmcampbackend.service;

import com.utkarsh.filmcampbackend.dto.ReviewRequestDTO;
import com.utkarsh.filmcampbackend.dto.ReviewResponseDTO;
import com.utkarsh.filmcampbackend.model.Review;
import com.utkarsh.filmcampbackend.model.UserModel;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.repository.ReviewRepo;
import com.utkarsh.filmcampbackend.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;

    public CommentsService(ReviewRepo reviewRepo, UserRepo userRepo) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
    }

    public void postReview(ReviewRequestDTO requestDTO, UserPrincipal userPrincipal) {
        Review review=new Review();
        Review parent=null;
        if(requestDTO.getParentId()!=null){
            parent=reviewRepo.findById(requestDTO.getParentId()).orElse(null);
        }
        UserModel user=userRepo.getReferenceById(userPrincipal.getUserId());
        review.setContent(requestDTO.getContent());
        review.setUser(user);
        review.setMovieId(requestDTO.getMovieId());
        review.setParent(parent);
        reviewRepo.save(review);
    }

    public ReviewResponseDTO convertToDTO(Review review){
        ReviewResponseDTO dto=new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setMovieId(review.getMovieId());
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
//        List<Review> reviews=reviewRepo.findAllByMovieIdAndParentIsNull(movieId);
        List<Review> reviews=reviewRepo.findAllByMovieIdAndParentIsNullOrderByCreatedAtDesc(movieId);
        List<ReviewResponseDTO> response=new ArrayList<>();
        for(Review review:reviews){
            response.add(convertToDTO(review));
        }
        return response;
    }
}
