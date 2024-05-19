package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT a FROM Actor a JOIN a.movies m WHERE m.id = :movieId")
    List<Actor> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT a FROM Actor a JOIN a.series s WHERE s.id = :seriesId")
    List<Actor> findBySeriesId(@Param("seriesId") Long seriesId);
}
