package com.j0k3r.movies.dao.impl;

import com.j0k3r.movies.dao.IRoleDao;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.models.RoleEntity;
import com.j0k3r.movies.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleDao implements IRoleDao {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity getRoleById(Long id) throws RoleException {
        return roleRepository.findById(id).orElseThrow(
                () -> new RoleException("this role no exist in the database",473)
        );
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public void deleteRole(RoleEntity roleEntity) {
        roleRepository.delete(roleEntity);
    }

    @Override
    public Optional<RoleEntity> getRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
