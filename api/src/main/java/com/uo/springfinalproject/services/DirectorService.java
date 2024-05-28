package com.uo.springfinalproject.services;

import com.uo.springfinalproject.DTO.DirectorDTO;
import com.uo.springfinalproject.DTO.DirectorResponseDTO;
import com.uo.springfinalproject.models.Director;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.repositories.DirectorRepository;
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
public class DirectorService extends GenericService<Director, DirectorRepository> {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Transactional
    public DirectorResponseDTO createDirector(DirectorDTO directorDTO) {
        Director director = new Director();
        director.setName(directorDTO.getName());

        Set<Movie> movies = new HashSet<>();
        for (Long movieId : directorDTO.getMovieIds()) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            movies.add(movie);
        }
        director.setMovies(movies);

        Set<Series> series = new HashSet<>();
        for (Long seriesId : directorDTO.getSeriesIds()) {
            Series seriesEntity = seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.add(seriesEntity);
        }
        director.setSeries(series);

        Director savedDirector = getRepo().save(director);
        return new DirectorResponseDTO(savedDirector);
    }

    @Transactional
    public DirectorResponseDTO updateDirector(Long id, DirectorDTO directorDTO) {
        Director director = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        director.setName(directorDTO.getName());

        Set<Movie> movies = new HashSet<>();
        for (Long movieId : directorDTO.getMovieIds()) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            movies.add(movie);
        }
        director.setMovies(movies);

        Set<Series> series = new HashSet<>();
        for (Long seriesId : directorDTO.getSeriesIds()) {
            Series seriesEntity = seriesRepository.findById(seriesId)
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            series.add(seriesEntity);
        }
        director.setSeries(series);

        Director updatedDirector = getRepo().save(director);
        return new DirectorResponseDTO(updatedDirector);
    }

    @Transactional(readOnly = true)
    public DirectorResponseDTO getDirectorById(Long id) {
        Director director = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        return new DirectorResponseDTO(director);
    }

    @Transactional(readOnly = true)
    public List<DirectorResponseDTO> getAllDirectors() {
        List<Director> directors = getRepo().findAll();
        return directors.stream()
                .map(DirectorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteDirector(Long id) {
        Director director = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found"));
        getRepo().delete(director);
        return true;
    }
}
