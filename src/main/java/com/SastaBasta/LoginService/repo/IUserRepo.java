package com.SastaBasta.LoginService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SastaBasta.LoginService.entities.User;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer>{
	 Optional<User> findByUsername(String username);
	 Boolean existsByUsername(String username);
	
}
