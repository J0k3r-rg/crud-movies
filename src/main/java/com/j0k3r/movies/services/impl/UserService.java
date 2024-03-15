package com.j0k3r.movies.services.impl;

import com.j0k3r.movies.dao.IUserDao;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.http.request.UserRequest;
import com.j0k3r.movies.http.response.UserResponse;
import com.j0k3r.movies.models.UserEntity;
import com.j0k3r.movies.services.IUserService;
import com.j0k3r.movies.utils.UserUtils;
import com.j0k3r.movies.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserUtils userUtils;

    @Override
    public List<UserResponse> findAllUsers() {
        return userUtils.mappingUsersByUserResponse(userDao.getAllUsers());
    }

    @Override
    public UserResponse findUserByUsername(String username) throws UserException, PropertiesException {
        Utils.validateString(username);
        return userUtils.mappingUserToUserResponse(userDao.getUserByUsername(username));
    }

    @Override
    public UserResponse findUserById(Long id) throws UserException, PropertiesException {
        Utils.validateIdLong(id);
        return userUtils.mappingUserToUserResponse(userDao.getUserById(id));
    }

    @Override
    public UserEntity saveUser(UserRequest userRequest) throws UserException, RoleException {
        userUtils.validateUserRequest(userRequest);
        return userDao.saveUser(userUtils.mappingUserRequestToUser(userRequest));
    }

    @Override
    public UserEntity updateUser(Long id, UserRequest userRequest) throws UserException, PropertiesException, RoleException {
        userUtils.validateUserRequest(userRequest);
        Utils.validateIdLong(id);
        return userDao.updateUser(userUtils.updateUserWithUserRequest(userDao.getUserById(id),userRequest));
    }

    @Override
    public UserEntity deleteUserById(Long id) throws UserException {
        UserEntity user = userDao.getUserById(id);
        userDao.deleteUser(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user;
        try {
            user = userDao.getUserByUsername(username);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
