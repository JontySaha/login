package com.SastaBasta.LoginService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SastaBasta.LoginService.entities.Admin;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer>{

}
