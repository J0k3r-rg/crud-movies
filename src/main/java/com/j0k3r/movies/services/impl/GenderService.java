package com.j0k3r.movies.services.impl;

import com.j0k3r.movies.dao.impl.GenderDao;
import com.j0k3r.movies.http.request.GenderRequest;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.models.Gender;
import com.j0k3r.movies.services.IGenderService;
import com.j0k3r.movies.utils.GenderUtils;
import com.j0k3r.movies.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("genderService")
public class GenderService implements IGenderService{

    @Autowired
    private GenderDao genderDao;

    @Autowired
    private GenderUtils genderUtils;

    @Override
    public Gender save(GenderRequest genderRequest) throws GenderException {
        genderUtils.validateGenderRequest(genderRequest);
        if (genderDao.findGenderByName(genderRequest.getName()).isPresent())
            throw new GenderException("This gender already exists in database");

        return genderDao.saveGender(genderUtils.mappingGenderRequestToGender(genderRequest));
    }

    @Override
    public GenderRequest findById(Long id) throws PropertiesException, GenderException {
        Utils.validateIdLong(id);
        return genderUtils.mappingGenderToGenderRequest(genderDao.findGenderById(id));
    }

    @Override
    public List<GenderRequest> findAll() {
        return genderUtils.mappingGendersToGendersRequests(genderDao.getAllGenders());
    }

    @Override
    public Gender deleteById(Long id) throws PropertiesException, GenderException {
        Utils.validateIdLong(id);
        Gender gender = genderDao.findGenderById(id);
        genderDao.deleteGender(gender);
        return gender;
    }

    @Override
    public Gender update(Long id, GenderRequest genderRequest) throws PropertiesException, GenderException {
        Utils.validateIdLong(id);
        genderUtils.validateGenderRequest(genderRequest);
        Gender gender = genderDao.findGenderById(id);
        return genderDao.updateGender(genderUtils.updateGenderToGenderRequest(gender, genderRequest));
    }
}
