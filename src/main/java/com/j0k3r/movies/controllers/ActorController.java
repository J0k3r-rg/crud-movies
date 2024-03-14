package com.j0k3r.movies.controllers;

import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.request.ActorRequest;
import com.j0k3r.movies.models.Actor;
import com.j0k3r.movies.services.impl.ActorService;
import com.j0k3r.movies.utils.ErrorResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Long id){
        ActorRequest actorRequest;
        try{
            actorRequest = actorService.findById(id);
        }  catch (ActorException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400, e.getMessage())
            );
        }
        return ResponseEntity.ok(actorRequest);
    }

    @PostMapping
    public ResponseEntity<?> saveActor(@RequestBody ActorRequest actorRequest)   {
        Actor actor;
        try{
            actor = actorService.save(actorRequest);
        } catch (ActorException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400,e.getMessage())
            );
        }
        return ResponseEntity.ok(actor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id,@RequestBody ActorRequest actorRequest){
        Actor actor;
        try{
            actor = actorService.update(id,actorRequest);
        }catch(ActorException | PropertiesException e){
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400, e.getMessage())
            );
        }
        return ResponseEntity.ok(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Long id){
        Actor actor;
        try {
            actor = actorService.deleteById(id);
        } catch (ActorException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(400, e.getMessage())
            );
        }
        return ResponseEntity.ok(actor);
    }

}