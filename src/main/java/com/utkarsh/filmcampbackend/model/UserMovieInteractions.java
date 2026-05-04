package com.utkarsh.filmcampbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_user_id", "movie_id"})
)
public class UserMovieInteractions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private MovieEntity movie;
    @ManyToOne
    private UserModel user;
    private boolean watched=false;
    private boolean liked=false;
    private float rating=0;
    private boolean in_watchlist=false;
}
