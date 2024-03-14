package com.j0k3r.movies.dao.impl;

import com.j0k3r.movies.dao.IGenderDao;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.models.Gender;
import com.j0k3r.movies.repositories.GenderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenderDao implements IGenderDao {

    @Autowired
    private GenderRepository genderRepository;


    @Override
    public Gender findGenderById(Long id) throws GenderException {
        return genderRepository.findById(id).orElseThrow(
                () -> new GenderException("This gender id not found in database")
        );
    }

    @Transactional
    @Override
    public Gender saveGender(Gender element) {
        return genderRepository.save(element);
    }

    @Transactional
    @Override
    public void deleteGender(Gender element) {
        genderRepository.delete(element);
    }

    @Transactional
    @Override
    public Gender updateGender(Gender element) {
        return genderRepository.save(element);
    }

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public Optional<Gender> findGenderByName(String name) {
        return genderRepository.findByName(name);
    }
}
