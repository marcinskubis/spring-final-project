package com.uo.springfinalproject.DTO;

import lombok.Data;
import java.util.Set;

@Data
public class ActorDTO {
    private String name;
    private Set<Long> movieIds;
    private Set<Long> seriesIds;
}
