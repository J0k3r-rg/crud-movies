package com.j0k3r.movies.utils;

import com.j0k3r.movies.http.request.GenderRequest;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.models.Gender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenderUtils {

    public Gender mappingGenderRequestToGender(GenderRequest genderRequest){
        return Gender.builder()
                .name(genderRequest.getName())
                .build();
    }

    public GenderRequest mappingGenderToGenderRequest(Gender gender){
        return GenderRequest.builder()
                .name(gender.getName())
                .build();
    }

    public Gender updateGenderToGenderRequest(Gender gender, GenderRequest genderRequest){
        gender.setName(genderRequest.getName());
        return gender;
    }

    public List<GenderRequest> mappingGendersToGendersRequests(List<Gender> genders){
        if (genders.isEmpty()) return List.of();
        return genders.stream().map(
                this::mappingGenderToGenderRequest
        ).toList();
    }

    public void validateGenderRequest(GenderRequest genderRequest) throws GenderException {
        if (genderRequest.getName() == null || genderRequest.getName().isEmpty()) {
            throw new GenderException("Name's gender is required", 434);
        }
    }

}
