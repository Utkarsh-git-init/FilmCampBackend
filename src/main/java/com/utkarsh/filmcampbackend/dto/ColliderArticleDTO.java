package com.utkarsh.filmcampbackend.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ColliderArticleDTO {
    private String title;
    private String link;
    private String description;
    private String imageUrl;
    private String author;
    private Instant publishedDate;
    private List<String> categories;
}
