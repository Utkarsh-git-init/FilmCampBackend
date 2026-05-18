package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    @JsonProperty(value = "user_id")
    int userId;
    String username;
}
