package com.j0k3r.movies.dao.impl;

import com.j0k3r.movies.dao.IUserDao;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.models.UserEntity;
import com.j0k3r.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao implements IUserDao {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(
                () -> new UserException("This user no exist in the database", 463)
        );
    }

    @Override
    public UserEntity getUserByUsername(String username) throws UserException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserException("This user no exist in the database",463)
        );
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
