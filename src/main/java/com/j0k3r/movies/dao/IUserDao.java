package com.j0k3r.movies.dao;

import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.models.UserEntity;

import java.util.List;

public interface IUserDao {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity getUserById(Long id) throws UserException;

    UserEntity getUserByUsername(String username) throws UserException;

    List<UserEntity> getAllUsers();

    UserEntity updateUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

}
