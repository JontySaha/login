package com.SastaBasta.LoginService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SastaBasta.LoginService.entities.Role;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
