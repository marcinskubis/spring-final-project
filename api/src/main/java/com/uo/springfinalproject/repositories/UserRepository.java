package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByRole(String role);

    @Query("SELECT u FROM User u WHERE u.id IN (SELECT r.user.id FROM Review r WHERE r.movie.id = :movieId OR r.series.id = :seriesId)")
    List<User> findByReviewForMovieOrSeries(@Param("movieId") Long movieId, @Param("seriesId") Long seriesId);
}
