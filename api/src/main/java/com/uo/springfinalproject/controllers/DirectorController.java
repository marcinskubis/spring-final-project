package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.DirectorDTO;
import com.uo.springfinalproject.DTO.DirectorResponseDTO;
import com.uo.springfinalproject.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> addDirector(@RequestBody DirectorDTO directorDTO) {
        DirectorResponseDTO createdDirector = directorService.createDirector(directorDTO);
        return ResponseEntity.ok(createdDirector);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable Long id) {
        DirectorResponseDTO director = directorService.getDirectorById(id);
        return ResponseEntity.ok(director);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        DirectorResponseDTO updatedDirector = directorService.updateDirector(id, directorDTO);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        boolean deleted = directorService.deleteDirector(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectors() {
        List<DirectorResponseDTO> directors = directorService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }
}
