package com.utkarsh.filmcampbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoviePersonnelDTO {
    int id;
    @JsonProperty("known_for_department")
    String knownForDepartment;
    String name;
    String character;
    int gender;
    @JsonProperty("profile_path")
    String profilePath;
    String job;

    public void completeProfilePath(){
        if(this.profilePath!=null)
            this.profilePath="https://media.themoviedb.org/t/p/w300_and_h450_face"+this.profilePath;
    }
}
