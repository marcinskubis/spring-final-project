package com.uo.springfinalproject.DTO;

import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.models.Series;
import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ActorResponseDTO {
    private Long id;
    private String name;
    private Set<String> movieTitles;
    private Set<String> seriesTitles;

    public ActorResponseDTO(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getName();
        this.movieTitles = actor.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet());
        this.seriesTitles = actor.getSeries().stream().map(Series::getTitle).collect(Collectors.toSet());
    }
}
