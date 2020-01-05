package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
    Long id;
    String slug;
    String title;
    String tagline;
    String overview;
    String releaseDate;
    int runtime;
    double voteAverage;
    List<String> companies;
    List<String> countries;
    List<String> genres;
    List<String> languages;
}
