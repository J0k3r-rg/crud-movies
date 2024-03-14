package com.j0k3r.movies.controllers;

import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.http.request.UserRequest;
import com.j0k3r.movies.http.response.UserResponse;
import com.j0k3r.movies.services.IUserService;
import com.j0k3r.movies.utils.ErrorResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        UserResponse userResponse;
        try {
            userResponse = userService.findUserById(id);
        } catch (UserException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find_by_username")
    public ResponseEntity<?> findUserByUsername(@RequestParam String username){
        UserResponse userResponse;
        try {
            userResponse = userService.findUserByUsername(username);
        } catch (UserException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest){
        try {
            userService.saveUser(userRequest);
        } catch (UserException | RoleException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(userRequest.getUsername());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        try {
            userService.updateUser(id,userRequest);
        } catch (UserException | PropertiesException | RoleException e) {
            ErrorResponseUtils.generateErrorResponse(e);
        }
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
        } catch (UserException e) {
            ErrorResponseUtils.generateErrorResponse(e);
        }
        return ResponseEntity.ok(id);
    }

}
