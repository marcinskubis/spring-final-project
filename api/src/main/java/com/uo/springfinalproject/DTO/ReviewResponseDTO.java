package com.uo.springfinalproject.DTO;

import com.uo.springfinalproject.models.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private int rating;
    private String userName;
    private String movieTitle;
    private String seriesTitle;

    public ReviewResponseDTO(Review review) {
        this.setId(review.getId());
        this.setContent(review.getContent());
        this.setRating(review.getRating());
        this.setUserName(review.getUser().getUsername());
    }
}

