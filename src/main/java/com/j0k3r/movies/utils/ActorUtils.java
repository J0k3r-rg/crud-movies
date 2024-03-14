package com.j0k3r.movies.utils;

import com.j0k3r.movies.dao.IActorDao;
import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.http.request.ActorRequest;
import com.j0k3r.movies.models.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActorUtils {

    @Autowired
    private IActorDao actorDao;

    public void validateActorRequest(ActorRequest actorRequest) throws ActorException{
        if (actorRequest.getName() == null || actorRequest.getName().isEmpty())
            throw new ActorException("Name's actor is required", 424);
        if (actorRequest.getUrlImage() == null || actorRequest.getUrlImage().isEmpty())
            throw new ActorException("LastName's actor is required", 425);
    }

    public Actor mappingActorRequestToActor(ActorRequest actorRequest) {
        return Actor.builder()
                .name(actorRequest.getName())
                .urlImage(actorRequest.getUrlImage())
                .build();
    }

    public ActorRequest mappingActorToActorRequest(Actor actor){
        return ActorRequest.builder()
                .name(actor.getName())
                .urlImage(actor.getUrlImage())
                .build();
    }

    public List<ActorRequest> mappingActorsToActorsRequests(List<Actor> actors){
        if (actors == null || actors.isEmpty()) return List.of();
        return actors.stream().map(
                this::mappingActorToActorRequest
        ).toList();
    }

    public Actor updateGenderToGenderRequest(Actor actor, ActorRequest actorRequest){
        actor.setName(actorRequest.getName());
        actor.setUrlImage(actorRequest.getUrlImage());
        return actor;
    }

    public List<Actor> listActorsByIdsList(List<Long> ids) throws RuntimeException{
        return ids.stream().map(
                id -> {
                    try {
                        return apply(id);
                    } catch (ActorException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }

    private Actor apply(Long id) throws ActorException {
        Actor actor;
        actor = actorDao.findById(id);
        return actor;
    }
}
