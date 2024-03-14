package com.j0k3r.movies.repositories;

import com.j0k3r.movies.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByRole(String role);
}
