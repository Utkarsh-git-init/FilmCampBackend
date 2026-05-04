package com.utkarsh.filmcampbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreditsDTO {
    private int id;
    private List<MoviePersonnelDTO> cast;
    private List<MoviePersonnelDTO> crew;
}
