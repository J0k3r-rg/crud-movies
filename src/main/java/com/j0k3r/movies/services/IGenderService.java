package com.j0k3r.movies.services;

import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.GenderRequest;
import com.j0k3r.movies.models.Gender;

public interface IGenderService{

    Gender save(GenderRequest genderRequest) throws GenderException;

    GenderRequest findById(Long id) throws PropertiesException, GenderException;

    Iterable<GenderRequest> findAll();

    Gender deleteById(Long id) throws PropertiesException, GenderException;

    Gender update(Long id, GenderRequest genderRequest) throws PropertiesException, GenderException;

}
