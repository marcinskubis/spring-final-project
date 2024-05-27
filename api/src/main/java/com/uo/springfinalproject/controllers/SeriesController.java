package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.DTO.SeriesDTO;
import com.uo.springfinalproject.DTO.SeriesResponseDTO;
import com.uo.springfinalproject.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @PostMapping
    public ResponseEntity<SeriesResponseDTO> addSeries(@RequestBody SeriesDTO seriesDTO) {
        SeriesResponseDTO createdSeries = seriesService.createSeries(seriesDTO);
        return ResponseEntity.ok(createdSeries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> getSeriesById(@PathVariable Long id) {
        SeriesResponseDTO series = seriesService.getSeriesById(id);
        return ResponseEntity.ok(series);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> updateSeries(@PathVariable Long id, @RequestBody SeriesDTO seriesDTO) {
        SeriesResponseDTO updatedSeries = seriesService.updateSeries(id, seriesDTO);
        return ResponseEntity.ok(updatedSeries);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        boolean deleted = seriesService.deleteSeries(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SeriesResponseDTO>> getAllSeries() {
        List<SeriesResponseDTO> seriesList = seriesService.getAllSeries();
        return ResponseEntity.ok(seriesList);
    }
}
