package com.uo.springfinalproject.services;

import com.uo.springfinalproject.models.Actor;
import com.uo.springfinalproject.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ActorService extends GenericService<Actor, ActorRepository>{
    @Transactional(readOnly = true)
    public List<Actor> findActorsByKeyword(String keyword) {
        return getRepo().findByNameContainingIgnoreCase(keyword);
    }
}
