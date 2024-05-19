package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping
    public Actor addActor(@RequestBody Actor actor) {
        return actorService.add(actor);
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.getById(id);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        actor.setId(id);
        return actorService.edit(actor);
    }

    @DeleteMapping("/{id}")
    public boolean deleteActor(@PathVariable Long id) {
        Actor actor = actorService.getById(id);
        return actorService.delete(actor);
    }

    @GetMapping("/search")
    public List<Actor> searchActors(@RequestParam String keyword) {
        return actorService.findActorsByKeyword(keyword);
    }
}