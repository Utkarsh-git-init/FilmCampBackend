package com.utkarsh.filmcampbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "movie_id")
    Integer movieId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;
    String content;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    Review parent;
    @CreationTimestamp
    @Column(name = "created_at")
    OffsetDateTime createdAt;
    @OneToMany(mappedBy = "parent")
    @OrderBy("createdAt DESC")
    List<Review> replies;
}
