package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

    @Query("SELECT r FROM Review r WHERE r.movie.id = :movieId")
    List<Review> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT r FROM Review r WHERE r.series.id = :seriesId")
    List<Review> findBySeriesId(@Param("seriesId") Long seriesId);
}
