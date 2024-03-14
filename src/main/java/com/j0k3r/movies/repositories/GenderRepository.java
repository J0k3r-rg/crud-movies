package com.j0k3r.movies.repositories;

import com.j0k3r.movies.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

    Optional<Gender> findByName(String name);

}
