package com.j0k3r.movies.services;


import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.http.request.UserRequest;
import com.j0k3r.movies.http.response.UserResponse;
import com.j0k3r.movies.models.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserResponse> findAllUsers();

    UserResponse findUserByUsername(String username) throws UserException, PropertiesException;

    UserResponse findUserById(Long id) throws UserException, PropertiesException;

    UserEntity saveUser(UserRequest userRequest) throws UserException, RoleException;

    UserEntity updateUser(Long id, UserRequest userRequest) throws UserException, PropertiesException, RoleException;

    UserEntity deleteUserById(Long id) throws UserException;

}
