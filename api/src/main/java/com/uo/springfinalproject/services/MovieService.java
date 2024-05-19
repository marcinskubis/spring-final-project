package com.uo.springfinalproject.services;

import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MovieService extends GenericService<Movie, MovieRepository>{
    @Transactional(readOnly = true)
    public List<Movie> findMoviesByKeyword(String keyword) {
        return getRepo().findByTitleContainingIgnoreCase(keyword);
    }
}
