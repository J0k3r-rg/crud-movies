package com.j0k3r.movies.controllers;

import com.j0k3r.movies.http.request.GenderRequest;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.models.Gender;
import com.j0k3r.movies.services.IGenderService;
import com.j0k3r.movies.utils.ErrorResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gender")
public class GenderController {

    @Autowired
    @Qualifier("genderService")
    private IGenderService genderService;

    @GetMapping
    public ResponseEntity<?> getAllGenders() {
        return ResponseEntity.ok(genderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenderById(@PathVariable Long id){
        GenderRequest gender;
        try{
            gender = genderService.findById(id);
        } catch(PropertiesException | GenderException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(gender);
    }


    @PostMapping
    public ResponseEntity<?> saveGender(@RequestBody GenderRequest genderRequest) {
        Gender gender;
        try {
            gender = genderService.save(genderRequest);
        }catch (GenderException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(gender);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGender(@PathVariable Long id, @RequestBody GenderRequest genderRequest){
        Gender gender;
        try{
            gender = genderService.update(id, genderRequest);
        } catch (PropertiesException | GenderException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(gender);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGender(@PathVariable Long id){
        Gender gender;
        try{
            gender = genderService.deleteById(id);
        } catch (PropertiesException | GenderException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(gender);
    }

}
