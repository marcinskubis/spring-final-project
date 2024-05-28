package com.uo.springfinalproject.services;

import com.uo.springfinalproject.DTO.SeriesDTO;
import com.uo.springfinalproject.DTO.SeriesResponseDTO;
import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Director;
import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.repositories.ActorRepository;
import com.uo.springfinalproject.repositories.DirectorRepository;
import com.uo.springfinalproject.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeriesService extends GenericService<Series, SeriesRepository> {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Transactional
    public SeriesResponseDTO createSeries(SeriesDTO seriesDTO) {
        Series series = new Series();
        series.setTitle(seriesDTO.getTitle());
        series.setDescription(seriesDTO.getDescription());
        series.setReleaseDate(seriesDTO.getReleaseDate());

        Director director = directorRepository.findById(seriesDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found"));
        series.setDirector(director);

        Set<Actor> actors = new HashSet<>();
        for (Long actorId : seriesDTO.getActorIds()) {
            Actor actor = actorRepository.findById(actorId)
                    .orElseThrow(() -> new RuntimeException("Actor not found"));
            actors.add(actor);
        }
        series.setActors(actors);

        Series savedSeries = getRepo().save(series);
        return new SeriesResponseDTO(savedSeries);
    }

    @Transactional
    public SeriesResponseDTO updateSeries(Long id, SeriesDTO seriesDTO) {
        Series series = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        series.setTitle(seriesDTO.getTitle());
        series.setDescription(seriesDTO.getDescription());
        series.setReleaseDate(seriesDTO.getReleaseDate());

        Director director = directorRepository.findById(seriesDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found"));
        series.setDirector(director);

        Set<Actor> actors = new HashSet<>();
        for (Long actorId : seriesDTO.getActorIds()) {
            Actor actor = actorRepository.findById(actorId)
                    .orElseThrow(() -> new RuntimeException("Actor not found"));
            actors.add(actor);
        }
        series.setActors(actors);

        Series updatedSeries = getRepo().save(series);
        return new SeriesResponseDTO(updatedSeries);
    }

    @Transactional(readOnly = true)
    public SeriesResponseDTO getSeriesById(Long id) {
        Series series = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        return new SeriesResponseDTO(series);
    }

    @Transactional(readOnly = true)
    public List<SeriesResponseDTO> getAllSeries() {
        List<Series> seriesList = getRepo().findAll();
        return seriesList.stream()
                .map(SeriesResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteSeries(Long id) {
        Series series = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        getRepo().delete(series);
        return true;
    }
}
