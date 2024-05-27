package com.uo.springfinalproject.DTO;

import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.models.Director;
import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class DirectorResponseDTO {
    private Long id;
    private String name;
    private Set<String> movieTitles;
    private Set<String> seriesTitles;

    public DirectorResponseDTO(Director director) {
        this.id = director.getId();
        this.name = director.getName();
        this.movieTitles = director.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet());
        this.seriesTitles = director.getSeries().stream().map(Series::getTitle).collect(Collectors.toSet());
    }
}
