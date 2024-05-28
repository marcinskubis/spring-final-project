package com.uo.springfinalproject.DTO;

import lombok.Data;
import java.sql.Date;
import java.util.Set;

@Data
public class SeriesDTO {
    private String title;
    private String description;
    private Date releaseDate;
    private Long directorId;
    private Set<Long> actorIds;
}
