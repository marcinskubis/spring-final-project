package com.uo.springfinalproject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private String content;
    private int rating;
    private Long userId;
    private Long movieId;
    private Long seriesId;
}