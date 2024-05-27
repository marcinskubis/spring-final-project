package com.uo.springfinalproject.DTO;

import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Series;
import lombok.Data;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SeriesResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private String directorName;
    private Set<String> actorNames;

    public SeriesResponseDTO(Series series) {
        this.id = series.getId();
        this.title = series.getTitle();
        this.description = series.getDescription();
        this.releaseDate = series.getReleaseDate();
        this.directorName = series.getDirector().getName();
        this.actorNames = series.getActors().stream().map(Actor::getName).collect(Collectors.toSet());
    }
}
