package com.example.currenda.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "user_id"}))
@Getter
@Setter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "director")
    private String director;
    @Column(name = "releasedate")
    private LocalDate releaseDate;
    @Column(name = "voteaverage")
    private String voteAverage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Movies(String title, String director, User user, LocalDate releaseDate, String voteAverage) {
        this.title = title;
        this.director = director;
        this.user = user;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }
}