package com.j0k3r.movies.controllers;

import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.exceptions.RoleException;
import com.j0k3r.movies.http.request.RoleRequest;
import com.j0k3r.movies.services.IRoleService;
import com.j0k3r.movies.utils.ErrorResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> findAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRole(@PathVariable Long id){
        RoleRequest roleRequest;
        try {
            roleRequest = roleService.findRoleById(id);
        } catch (RoleException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(roleRequest);
    }

    @PostMapping
    public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest){
        try {
            roleService.saveRole(roleRequest);
        } catch (RoleException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(roleRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleRequest roleRequest){
        try {
            roleService.updateRole(id,roleRequest);
        } catch (RoleException | PropertiesException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        try {
            roleService.deleteRoleById(id);
        } catch (RoleException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponseUtils.generateErrorResponse(e)
            );
        }
        return ResponseEntity.ok(id);
    }
}
