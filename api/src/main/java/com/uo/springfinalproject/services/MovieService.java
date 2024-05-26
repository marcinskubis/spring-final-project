package com.uo.springfinalproject.services;

import com.uo.springfinalproject.DTO.MovieDTO;
import com.uo.springfinalproject.DTO.MovieResponseDTO;
import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.models.Director;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.repositories.ActorRepository;
import com.uo.springfinalproject.repositories.DirectorRepository;
import com.uo.springfinalproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService extends GenericService<Movie, MovieRepository>{
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ActorRepository actorRepository;
    @Transactional(readOnly = true)
    public List<Movie> findMoviesByKeyword(String keyword) {
        return getRepo().findByTitleContainingIgnoreCase(keyword);
    }

    @Transactional
    public MovieResponseDTO createMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        Director director = directorRepository.findById(movieDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director not found"));
        movie.setDirector(director);

        Set<Actor> actors = new HashSet<>();
        for (Long actorId : movieDTO.getActorIds()) {
            Actor actor = actorRepository.findById(actorId)
                    .orElseThrow(() -> new RuntimeException("Actor not found"));
            actors.add(actor);
        }
        movie.setActors(actors);

        Movie savedMovie = getRepo().save(movie);
        return new MovieResponseDTO(savedMovie);
    }
}
