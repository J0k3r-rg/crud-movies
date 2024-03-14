package com.j0k3r.movies.utils;

import com.j0k3r.movies.dao.IUserDao;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.exceptions.UserException;
import com.j0k3r.movies.http.request.UserRequest;
import com.j0k3r.movies.http.response.UserResponse;
import com.j0k3r.movies.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtils {

    @Autowired
    private RoleUtils roleUtils;

    @Autowired
    private IUserDao userDao;

    public void validateUserRequest(UserRequest userRequest) throws UserException {
        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty())
            throw new UserException("Name for user is empty or null",464);
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty())
            throw new UserException("Password for user is empty or null",464);
        if (userRequest.getIdRoles() == null || userRequest.getIdRoles().isEmpty())
            throw new UserException("List of roles is null or empty",465);
        for(Long role : userRequest.getIdRoles()){
            if (role == null || role <=0)
                throw new UserException("id from list roles is null o invalid",466);
        }
    }

    public List<UserResponse> mappingUsersByUserResponse(List<UserEntity> allUsers) {
        return allUsers.stream().map(
            this::mappingUserToUserResponse
        ).toList();
    }

    public UserResponse mappingUserToUserResponse(UserEntity user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .idRoles(roleUtils.mappingRolesToRoleRequest(user.getRoles()))
                .active(user.getActivate())
                .build();
    }

    public UserEntity mappingUserRequestToUser(UserRequest userRequest) throws RoleException {
        return UserEntity.builder()
                .username(userRequest.getUsername())
                .password(this.passwordEncoder().encode(userRequest.getPassword()))
                .roles(roleUtils.mappingRolesRequestToRole(userRequest.getIdRoles()))
                .build();
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserEntity updateUserWithUserRequest(UserEntity user, UserRequest userRequest) throws UserException, RoleException {
        user.setUsername(userRequest.getUsername());
        user.setActivate(userRequest.getActivate());
        user.setRoles(roleUtils.mappingRolesRequestToRole(userRequest.getIdRoles()));
        return user;
    }
}
