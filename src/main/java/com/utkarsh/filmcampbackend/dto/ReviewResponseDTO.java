package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ReviewResponseDTO {
    Integer id;
    @JsonProperty(value = "movie_id")
    Integer movieId;
    @JsonProperty(value = "user_id")
    Integer userId;
    @JsonProperty(value = "username")
    String username;
    String content;
    @JsonProperty(value = "created_at")
    OffsetDateTime createdAt;
    List<ReviewResponseDTO> replies;
}
