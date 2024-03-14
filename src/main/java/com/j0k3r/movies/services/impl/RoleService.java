package com.j0k3r.movies.services.impl;

import com.j0k3r.movies.dao.IRoleDao;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.http.request.RoleRequest;
import com.j0k3r.movies.models.RoleEntity;
import com.j0k3r.movies.services.IRoleService;
import com.j0k3r.movies.utils.RoleUtils;
import com.j0k3r.movies.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private RoleUtils roleUtils;

    @Override
    public List<RoleRequest> findAllRoles() {
        return roleUtils.mappingRolesToRoleRequest(roleDao.getAllRoles());
    }

    @Override
    public RoleRequest findRoleById(Long id) throws RoleException {
        return roleUtils.mappingRoleToRoleRequest(roleDao.getRoleById(id));
    }

    @Override
    public RoleEntity saveRole(RoleRequest roleRequest) throws RoleException {
        if (roleDao.getRoleByRole(roleRequest.getRole()).isPresent())
            throw new RoleException("The role exist in the database",648);
        return roleDao.saveRole(roleUtils.mappingRoleRequestToRole(roleRequest));
    }

    @Override
    public RoleEntity updateRole(Long id, RoleRequest roleRequest) throws RoleException, PropertiesException {
        Utils.validateIdLong(id);
        roleUtils.validateRoleRequest(roleRequest);
        return roleUtils.updateRoleByRoleRequest(roleDao.getRoleById(id),roleRequest);
    }

    @Override
    public RoleEntity deleteRoleById(Long id) throws RoleException {
        RoleEntity role = roleDao.getRoleById(id);
        roleDao.deleteRole(role);
        return role;
    }

}
