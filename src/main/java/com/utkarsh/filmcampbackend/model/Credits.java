package com.utkarsh.filmcampbackend.model;

import lombok.Data;

import java.util.List;

@Data
public class Credits {
    private int id;
    private List<MoviePersonnel> cast;
    private List<MoviePersonnel> crew;
}
