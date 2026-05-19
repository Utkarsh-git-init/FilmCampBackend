package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utkarsh.filmcampbackend.model.MovieEntity;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    MovieEntity movie;
    @JsonProperty(value = "parent_id")
    Integer parentId;
    String content;
}
