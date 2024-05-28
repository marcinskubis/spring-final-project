package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.MovieDTO;
import com.uo.springfinalproject.DTO.MovieResponseDTO;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieDTO movieDTO) {
        MovieResponseDTO createdMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.ok(createdMovie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getById(id);
        MovieResponseDTO movieDTO = new MovieResponseDTO(movie);
        return ResponseEntity.ok(movieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieResponseDTO updatedMovie = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> searchMovies(@RequestParam String keyword) {
        List<Movie> movies = movieService.findMoviesByKeyword(keyword);
        List<MovieResponseDTO> movieResponseDTOs = movies.stream()
                .map(MovieResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieResponseDTOs);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        List<MovieResponseDTO> movieResponseDTOs = movies.stream()
                .map(MovieResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movieResponseDTOs);
    }
}
