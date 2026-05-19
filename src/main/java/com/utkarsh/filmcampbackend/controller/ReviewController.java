package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.dto.ReviewRequestDTO;
import com.utkarsh.filmcampbackend.dto.ReviewResponseDTO;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add_review")
    public void postReview(@RequestBody ReviewRequestDTO requestDTO, @AuthenticationPrincipal UserPrincipal userPrincipal){
        reviewService.postReview(requestDTO,userPrincipal);
    }
    @GetMapping("/{movieId}/reviews")
    public List<ReviewResponseDTO> getReviews(@PathVariable int movieId){
        return reviewService.getReviews(movieId);
    }
}
