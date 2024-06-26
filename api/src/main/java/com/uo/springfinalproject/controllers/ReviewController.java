package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.ReviewDTO;
import com.uo.springfinalproject.DTO.ReviewResponseDTO;
import com.uo.springfinalproject.models.Review;
import com.uo.springfinalproject.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewResponseDTO createdReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(createdReview);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getById(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        return reviewService.edit(review);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReview(@PathVariable Long id) {
        Review review = reviewService.getById(id);
        return reviewService.delete(review);
    }

}
