package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReviewRequestDTO {
    @JsonProperty(value = "movie_id")
    Integer movieId;
    @JsonProperty(value = "parent_id")
    Integer parentId;
    String content;
}
