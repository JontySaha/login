package com.SastaBasta.LoginService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SastaBasta.LoginService.entities.Customer;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer>{
}
