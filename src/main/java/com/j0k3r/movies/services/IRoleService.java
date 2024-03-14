package com.j0k3r.movies.services;

import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.http.request.RoleRequest;
import com.j0k3r.movies.models.RoleEntity;

import java.util.List;

public interface IRoleService {

    List<RoleRequest> findAllRoles();

    RoleRequest findRoleById(Long id) throws RoleException;

    RoleEntity saveRole(RoleRequest roleRequest) throws RoleException;

    RoleEntity updateRole(Long id, RoleRequest roleRequest) throws RoleException, PropertiesException;

    RoleEntity deleteRoleById(Long id) throws RoleException;

}
