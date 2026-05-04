package com.utkarsh.filmcampbackend.dto;

import lombok.Data;

@Data
public class MoviePersonnelDTO {
    int id;
    String known_for_department;
    String name;
    String character;
    int gender;
    String profile_path;
    String job;

    public void completeProfilePath(){
        this.profile_path="https://media.themoviedb.org/t/p/w300_and_h450_face"+this.profile_path;
    }

}
