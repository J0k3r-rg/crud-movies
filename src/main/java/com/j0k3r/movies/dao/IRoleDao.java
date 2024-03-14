package com.j0k3r.movies.dao;

import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.models.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleDao {

    RoleEntity saveRole(RoleEntity roleEntity);

    RoleEntity getRoleById(Long id) throws RoleException;

    List<RoleEntity> getAllRoles();

    RoleEntity updateRole(RoleEntity roleEntity);

    void deleteRole(RoleEntity roleEntity);

    Optional<RoleEntity> getRoleByRole(String role);
}
