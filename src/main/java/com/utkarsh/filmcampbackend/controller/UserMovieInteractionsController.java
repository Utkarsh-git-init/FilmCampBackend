package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.dto.UserMovieInteractionsDTO;
import com.utkarsh.filmcampbackend.model.UserMovieInteractions;
import com.utkarsh.filmcampbackend.model.UserPrincipal;
import com.utkarsh.filmcampbackend.service.UserMovieInteractionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/interactions")
public class UserMovieInteractionsController {

    UserMovieInteractionsService service;
    UserMovieInteractionsController(UserMovieInteractionsService service){
        this.service=service;
    }

    @PostMapping("/movie/update")
    public ResponseEntity<?> updateState(@RequestBody UserMovieInteractionsDTO request, @AuthenticationPrincipal UserPrincipal userPrincipal){
        service.updateStates(request,userPrincipal);
        System.out.println("movie interaction updated");
        return ResponseEntity.ok().body("updated");
    }
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<?> getInteractionsState(@PathVariable int movieId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        UserMovieInteractionsDTO interactions=service.getInteractionState(movieId,userPrincipal);
        System.out.println("get interaction state");
        return ResponseEntity.ok()
            .body(Objects.requireNonNullElse(interactions, "Not yet interacted"));
    }
}
