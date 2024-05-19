package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.add(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        movie.setId(id);
        return movieService.edit(movie);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMovie(@PathVariable Long id) {
        Movie movie = movieService.getById(id);
        return movieService.delete(movie);
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String keyword) {
        return movieService.findMoviesByKeyword(keyword);
    }
}

