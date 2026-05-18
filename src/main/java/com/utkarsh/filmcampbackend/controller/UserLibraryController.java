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

    @GetMapping("/{username}/recent_activity")
    public ResponseEntity<?> getRecentlyInteractedMovies(@PathVariable String username){
       return ResponseEntity.ok()
               .body(userLibraryService.getRecentlyInteractedMovies(username));
    }
    @GetMapping("/{username}/watched")
    public ResponseEntity<List<MovieEntity>> getWatched(@PathVariable String username){
        return ResponseEntity.ok()
                .body(userLibraryService.getWatched(username));
    }
    @GetMapping("/{username}/liked")
    public ResponseEntity<List<MovieEntity>> getLiked(@PathVariable String username){
        return  ResponseEntity.ok()
                .body(userLibraryService.getLiked(username));
    }
    @GetMapping("/{username}/watchlist")
    public ResponseEntity<List<MovieEntity>> getWatchlist(@PathVariable String username){
        return ResponseEntity.ok()
                .body(userLibraryService.getWatchlist(username));
    }

}
