package com.SastaBasta.LoginService.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SastaBasta.LoginService.entities.Admin;
import com.SastaBasta.LoginService.entities.Role;
import com.SastaBasta.LoginService.entities.User;
import com.SastaBasta.LoginService.repo.IRoleRepo;
import com.SastaBasta.LoginService.repo.IUserRepo;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private IUserRepo userRepository;
	
	@Autowired
    private IRoleRepo roleRepository;
    
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User userDTO){
        // add check for username exists in a DB
        if(userRepository.existsByUsername(userDTO.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }       
        
        Admin admin=new Admin();
        
        // create user object
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));      
        user.setAdmin(admin);
        admin.setUser(user);
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
	
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
	
}
