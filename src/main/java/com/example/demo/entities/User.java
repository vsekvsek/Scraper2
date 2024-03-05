package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "user")
@ToString

public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 	private long id;
	 	
	 	private String name;
	 	private String email;
	 	private String password;
	 	
	 	public void setName(String aName) {
	 		this.name =aName;
	 	}
	 	public String getNAme() {
	 		return name;
	 	}
	 	public void setEmail(String aName) {
	 		this.email =aName;
	 	}
	 	public String getEmail() {
	 		return email;
	 	}
	 	public void setPassword(String aPass) {
	 		this.password =aPass;
	 	}
	 	public String getPassWord() {
	 		return password;
	 	}
}
