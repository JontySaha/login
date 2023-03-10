package com.SastaBasta.LoginService.entities;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor 
@NoArgsConstructor 
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custId;
	
	private String custName;
	private long mobileNo;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private User user;
}