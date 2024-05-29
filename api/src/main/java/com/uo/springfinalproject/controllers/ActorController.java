package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.ActorDTO;
import com.uo.springfinalproject.DTO.ActorResponseDTO;
import com.uo.springfinalproject.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@CrossOrigin("*")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping
    public ResponseEntity<ActorResponseDTO> addActor(@RequestBody ActorDTO actorDTO) {
        ActorResponseDTO createdActor = actorService.createActor(actorDTO);
        return ResponseEntity.ok(createdActor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Long id) {
        ActorResponseDTO actor = actorService.getActorById(id);
        return ResponseEntity.ok(actor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        ActorResponseDTO updatedActor = actorService.updateActor(id, actorDTO);
        return ResponseEntity.ok(updatedActor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        boolean deleted = actorService.deleteActor(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDTO>> getAllActors() {
        List<ActorResponseDTO> actors = actorService.getAllActors();
        return ResponseEntity.ok(actors);
    }
}
