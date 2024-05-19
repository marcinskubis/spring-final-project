package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT m FROM Movie m WHERE YEAR(m.releaseDate) = :releaseYear")
    List<Movie> findByReleaseYear(@Param("releaseYear") int releaseYear);

    @Query("SELECT m FROM Movie m WHERE m.director.name = :directorName")
    List<Movie> findByDirectorName(@Param("directorName") String directorName);
}
