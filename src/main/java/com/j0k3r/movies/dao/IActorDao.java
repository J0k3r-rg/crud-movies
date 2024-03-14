package com.j0k3r.movies.dao;

import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.http.request.ActorRequest;
import com.j0k3r.movies.models.Actor;

import java.util.List;
import java.util.Optional;

public interface IActorDao {

    Actor save(Actor actor);

    Actor findById(Long id) throws ActorException;

    void delete(Actor actor);

    List<Actor> findAll();

    Actor update(Actor actor);

    Optional<Actor> findActorByName(String name);
}
