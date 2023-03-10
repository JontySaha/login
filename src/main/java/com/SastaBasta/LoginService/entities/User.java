package com.SastaBasta.LoginService.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "user")
	@JsonIgnore
    private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "user")
	@JsonIgnore
    private Admin admin;
}
