package com.uo.springfinalproject.repositories;

import com.uo.springfinalproject.models.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT s FROM Series s WHERE YEAR(s.releaseDate) = :releaseYear")
    List<Series> findByReleaseYear(@Param("releaseYear") int releaseYear);

    @Query("SELECT s FROM Series s WHERE s.director.name = :directorName")
    List<Series> findByDirectorName(@Param("directorName") String directorName);
}
