package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.models.Director;
import com.uo.springfinalproject.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getRepo().findAll();
    }

    @PostMapping
    public Director addDirector(@RequestBody Director director) {
        return directorService.add(director);
    }

    @GetMapping("/{id}")
    public Director getDirectorById(@PathVariable Long id) {
        return directorService.getById(id);
    }

    @PutMapping("/{id}")
    public Director updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id);
        return directorService.edit(director);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDirector(@PathVariable Long id) {
        Director director = directorService.getById(id);
        return directorService.delete(director);
    }

}
