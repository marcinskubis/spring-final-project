package com.uo.springfinalproject.services;

import com.uo.springfinalproject.models.Series;
import com.uo.springfinalproject.repositories.SeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SeriesService extends GenericService<Series, SeriesRepository>{
    @Transactional(readOnly = true)
    public List<Series> findSeriesByKeyword(String keyword) {
        return getRepo().findByTitleContainingIgnoreCase(keyword);
    }
}
