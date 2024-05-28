package com.uo.springfinalproject.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {
    private String title;
    private String description;
    private Date releaseDate;
    private Long directorId;
    private Set<Long> actorIds;
}
