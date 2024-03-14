package com.j0k3r.movies.dao;

import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.models.Gender;

import java.util.List;
import java.util.Optional;

public interface IGenderDao{

    Gender findGenderById(Long id) throws GenderException;

    Gender saveGender(Gender element);

    void deleteGender(Gender element);

    Gender updateGender(Gender element);

    List<Gender> getAllGenders();

    Optional<Gender> findGenderByName(String name);
}
