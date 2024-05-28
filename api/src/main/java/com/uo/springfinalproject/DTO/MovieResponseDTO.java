package com.uo.springfinalproject.DTO;

import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Movie;
import lombok.Data;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private String directorName;
    private Set<String> actorNames;

    public MovieResponseDTO (Movie movie) {
        this.setId(movie.getId());
        this.setTitle(movie.getTitle());
        this.setDescription(movie.getDescription());
        this.setReleaseDate(movie.getReleaseDate());
        this.setDirectorName(movie.getDirector().getName());
        this.setActorNames(movie.getActors().stream().map(Actor::getName).collect(Collectors.toSet()));
    }
}
