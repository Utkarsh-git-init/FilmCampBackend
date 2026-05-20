package com.utkarsh.filmcampbackend.controller;

import com.utkarsh.filmcampbackend.dto.ColliderArticleDTO;
import com.utkarsh.filmcampbackend.service.FeedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("/collider")
    public List<ColliderArticleDTO> getColliderFeed(){
        return feedService.getColliderFeed();
    }
}
