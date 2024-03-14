package com.j0k3r.movies.dao.impl;

import com.j0k3r.movies.dao.IActorDao;
import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.models.Actor;
import com.j0k3r.movies.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ActorDao implements IActorDao {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor findById(Long id) throws ActorException {
        return actorRepository.findById(id).orElseThrow(
                () -> new ActorException("Actor not found",423)
        );
    }

    @Override
    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor update(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Optional<Actor> findActorByName(String name) {
        return actorRepository.findByName(name);
    }
}
