package com.SastaBasta.LoginService.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SastaBasta.LoginService.entities.Role;
import com.SastaBasta.LoginService.entities.User;
import com.SastaBasta.LoginService.repo.IUserRepo;

@Service
public class LoginServiceImpl implements UserDetailsService{

	@Autowired
	IUserRepo userRepository;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found with username:" + username));
				return new org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	 private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
