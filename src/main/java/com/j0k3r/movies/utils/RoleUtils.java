package com.j0k3r.movies.utils;

import com.j0k3r.movies.dao.IRoleDao;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.http.request.RoleRequest;
import com.j0k3r.movies.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleUtils {

    @Autowired
    private IRoleDao roleDao;

    public RoleRequest mappingRoleToRoleRequest(RoleEntity role){
        return RoleRequest.builder()
                .role(role.getRole())
                .build();
    }

    public List<RoleRequest> mappingRolesToRoleRequest(List<RoleEntity> roles) {
        return roles.stream().map(
                this::mappingRoleToRoleRequest
        ).toList();
    }

    public List<RoleEntity> mappingRolesRequestToRole(List<Long> listIdRoles) throws RoleException {
        List<RoleEntity> roles = new ArrayList<>();
        for (Long id : listIdRoles){
            roles.add(roleDao.getRoleById(id));
        }
        return roles;
    }

    public RoleEntity mappingRoleRequestToRole(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .role(roleRequest.getRole())
                .build();
    }

    public void validateRoleRequest(RoleRequest roleRequest) throws RoleException {
        if (roleRequest.getRole() == null || roleRequest.getRole().isEmpty())
            throw new RoleException("",469);
    }

    public RoleEntity updateRoleByRoleRequest(RoleEntity roleById, RoleRequest roleRequest) {
        roleById.setRole(roleRequest.getRole());
        return roleById;
    }
}
