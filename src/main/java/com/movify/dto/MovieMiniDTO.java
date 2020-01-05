package com.movify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieMiniDTO {
    Long id;
    String slug;
    String title;
    String tagline;
    String releaseDate;
    double voteAverage;
    List<String> genres;
}
