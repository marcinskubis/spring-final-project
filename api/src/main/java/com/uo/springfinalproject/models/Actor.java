package com.uo.springfinalproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Actor extends Person implements IMainModel{
    private String name;
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;
    @ManyToMany(mappedBy = "actors")
    private Set<Series> series;
}
