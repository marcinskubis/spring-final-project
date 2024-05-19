package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    List<Director> findByNameContainingIgnoreCase(String name);

    @Query("SELECT d FROM Director d WHERE d.id IN (SELECT m.director.id FROM Movie m WHERE m.id = :movieId)")
    List<Director> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT d FROM Director d WHERE d.id IN (SELECT s.director.id FROM Series s WHERE s.id = :seriesId)")
    List<Director> findBySeriesId(@Param("seriesId") Long seriesId);
}
