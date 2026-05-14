package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.dto.ReviewRequestDTO;
import com.utkarsh.filmcampbackend.dto.ReviewResponseDTO;
import com.utkarsh.filmcampbackend.model.Review;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.service.CommentsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/add_review")
    public void postReview(@RequestBody ReviewRequestDTO requestDTO, @AuthenticationPrincipal UserPrincipal userPrincipal){
        commentsService.postReview(requestDTO,userPrincipal);
    }
    @GetMapping("/{movieId}/reviews")
    public List<ReviewResponseDTO> getReviews(@PathVariable int movieId){
        return commentsService.getReviews(movieId);
    }
}
