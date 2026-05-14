package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.model.MovieEntity;
import com.utkarsh.filmcampbackend.service.UserLibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/u")
public class UserLibraryController {
    private final UserLibraryService userLibraryService;

    public UserLibraryController(UserLibraryService userLibraryService){
        this.userLibraryService = userLibraryService;
    }

    @GetMapping("/{userId}/recent_activity")
    public ResponseEntity<?> getRecentlyInteractedMovies(@PathVariable int userId){
       return ResponseEntity.ok()
               .body(userLibraryService.getRecentlyInteractedMovies(userId));
    }
    @GetMapping("/{userId}/watched")
    public ResponseEntity<List<MovieEntity>> getWatched(@PathVariable int userId){
        return ResponseEntity.ok()
                .body(userLibraryService.getWatched(userId));
    }
    @GetMapping("/{userId}/liked")
    public ResponseEntity<List<MovieEntity>> getLiked(@PathVariable int userId){
        return  ResponseEntity.ok()
                .body(userLibraryService.getLiked(userId));
    }
    @GetMapping("/{userId}/watchlist")
    public ResponseEntity<List<MovieEntity>> getWatchlist(@PathVariable int userId){
        return ResponseEntity.ok()
                .body(userLibraryService.getWatchlist(userId));
    }

}
