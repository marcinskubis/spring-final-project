package com.uo.springfinalproject.controllers;

import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @PostMapping
    public Series addSeries(@RequestBody Series series) {
        return seriesService.add(series);
    }

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id) {
        return seriesService.getById(id);
    }

    @PutMapping("/{id}")
    public Series updateSeries(@PathVariable Long id, @RequestBody Series series) {
        series.setId(id);
        return seriesService.edit(series);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSeries(@PathVariable Long id) {
        Series series = seriesService.getById(id);
        return seriesService.delete(series);
    }

    @GetMapping("/search")
    public List<Series> searchSeries(@RequestParam String keyword) {
        return seriesService.findSeriesByKeyword(keyword);
    }
}
