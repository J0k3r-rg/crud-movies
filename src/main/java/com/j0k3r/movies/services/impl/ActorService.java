package com.j0k3r.movies.services.impl;

import com.j0k3r.movies.dao.IActorDao;
import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.ActorRequest;
import com.j0k3r.movies.models.Actor;
import com.j0k3r.movies.services.IActorService;
import com.j0k3r.movies.utils.ActorUtils;
import com.j0k3r.movies.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService implements IActorService {

    @Autowired
    private IActorDao actorDao;

    @Autowired
    private ActorUtils actorUtils;

    @Override
    public Actor save(ActorRequest actorRequest) throws ActorException {
        actorUtils.validateActorRequest(actorRequest);
        if (actorDao.findActorByName(actorRequest.getName()).isPresent())
            throw new ActorException("This actor already exists in database");
        return actorDao.save(actorUtils.mappingActorRequestToActor(actorRequest));
    }

    @Override
    public ActorRequest findById(Long id) throws PropertiesException, ActorException {
        Utils.validateIdLong(id);
        return actorUtils.mappingActorToActorRequest(actorDao.findById(id));
    }

    @Override
    public List<ActorRequest> findAll() {
        return actorUtils.mappingActorsToActorsRequests(actorDao.findAll());
    }

    @Override
    public Actor deleteById(Long id) throws PropertiesException, ActorException {
        Utils.validateIdLong(id);
        Actor actor = actorDao.findById(id);
        actorDao.delete(actor);
        return actor;
    }

    @Override
    public Actor update(Long id, ActorRequest actorRequest) throws ActorException, PropertiesException {
        Utils.validateIdLong(id);
        actorUtils.validateActorRequest(actorRequest);
        Actor actor = actorDao.findById(id);
        return actorDao.update(actorUtils.updateGenderToGenderRequest(actor, actorRequest));
    }

}
