package com.uo.springfinalproject.services;

import com.uo.springfinalproject.DTO.ReviewDTO;
import com.uo.springfinalproject.DTO.ReviewResponseDTO;
import com.uo.springfinalproject.DTO.SeriesResponseDTO;
import com.uo.springfinalproject.models.Movie;
import com.uo.springfinalproject.models.Review;
import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.models.User;
import com.uo.springfinalproject.repositories.MovieRepository;
import com.uo.springfinalproject.repositories.ReviewRepository;
import com.uo.springfinalproject.repositories.SeriesRepository;
import com.uo.springfinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService extends GenericService<Review, ReviewRepository>{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Transactional
    public ReviewResponseDTO createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());

        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        if (reviewDTO.getMovieId() != null) {
            Movie movie = movieRepository.findById(reviewDTO.getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            review.setMovie(movie);
        } else if (reviewDTO.getSeriesId() != null) {
            Series series = seriesRepository.findById(reviewDTO.getSeriesId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            review.setSeries(series);
        } else {
            throw new IllegalArgumentException("Either movieId or seriesId must be provided");
        }

        Review savedReview = reviewRepository.save(review);
        return new ReviewResponseDTO(savedReview);
    }

    @Transactional
    public ReviewResponseDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());

        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        if (reviewDTO.getMovieId() != null) {
            Movie movie = movieRepository.findById(reviewDTO.getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            review.setMovie(movie);
            review.setSeries(null);
        } else if (reviewDTO.getSeriesId() != null) {
            Series series = seriesRepository.findById(reviewDTO.getSeriesId())
                    .orElseThrow(() -> new RuntimeException("Series not found"));
            review.setSeries(series);
            review.setMovie(null);
        } else {
            throw new IllegalArgumentException("Either movieId or seriesId must be provided");
        }

        Review updatedReview = reviewRepository.save(review);
        return new ReviewResponseDTO(updatedReview);
    }

    @Transactional(readOnly = true)
    public ReviewResponseDTO getReviewById(Long id) {
        Review review = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        return new ReviewResponseDTO(review);
    }

    @Transactional
    public boolean deleteReview(Long id) {
        Review review = getRepo().findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
        getRepo().delete(review);
        return true;
    }
}
