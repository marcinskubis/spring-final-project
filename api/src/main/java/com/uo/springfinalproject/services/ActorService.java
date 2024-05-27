package com.uo.springfinalproject.services;

import com.uo.springfinalproject.DTO.ActorDTO;
import com.uo.springfinalproject.DTO.ActorResponseDTO;
import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.repositories.ActorRepository;
import com.uo.springfinalproject.repositories.MovieRepository;
import com.uo.springfinalproject.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorService extends GenericService<Actor, ActorRepository> {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Transactional
    public ActorResponseDTO createActor(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setName(actorDTO.getName());

        Set<Movie> movies = new HashSet<>();
        for (Long movieId : actorDTO.getMovieIds()) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            movies.add(movie);
        }
        actor.setMovies(movies);

        Set<Series> series = new HashSet<>();
        for (Long seriesId : actorDTO.getSeriesIds()) {
            Series seriesEntity = seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.add(seriesEntity);
        }
        actor.setSeries(series);

        Actor savedActor = getRepo().save(actor);
        return new ActorResponseDTO(savedActor);
    }

    @Transactional
    public ActorResponseDTO updateActor(Long id, ActorDTO actorDTO) {
        Actor actor = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        actor.setName(actorDTO.getName());

        Set<Movie> movies = new HashSet<>();
        for (Long movieId : actorDTO.getMovieIds()) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            movies.add(movie);
        }
        actor.setMovies(movies);

        Set<Series> series = new HashSet<>();
        for (Long seriesId : actorDTO.getSeriesIds()) {
            Series seriesEntity = seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.add(seriesEntity);
        }
        actor.setSeries(series);

        Actor updatedActor = getRepo().save(actor);
        return new ActorResponseDTO(updatedActor);
    }

    @Transactional(readOnly = true)
    public ActorResponseDTO getActorById(Long id) {
        Actor actor = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        return new ActorResponseDTO(actor);
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getAllActors() {
        List<Actor> actors = getRepo().findAll();
        return actors.stream()
                .map(ActorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteActor(Long id) {
        Actor actor = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        getRepo().delete(actor);
        return true;
    }
}
