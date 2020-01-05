package com.movify.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter @Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String slug;
    String title;
    String overview;
    String tagline;
    LocalDate releaseDate;
    int runtime;
    double voteAverage;
    @ManyToMany
    List<Genre> genres;
    @ManyToMany
    List<Company> companies;
    @ManyToMany
    List<Country> countries;
    @ManyToMany
    List<Language> languages;
}
