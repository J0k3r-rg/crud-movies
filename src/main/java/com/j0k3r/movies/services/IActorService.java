package com.j0k3r.movies.services;

import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.ActorRequest;
import com.j0k3r.movies.models.Actor;

import java.util.List;

public interface IActorService {

    Actor save(ActorRequest actor) throws ActorException;

    ActorRequest findById(Long id) throws PropertiesException, ActorException;

    List<ActorRequest> findAll();

    Actor deleteById(Long id) throws PropertiesException, ActorException;

    Actor update(Long id, ActorRequest actor) throws ActorException, PropertiesException;

}
