package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class User {
	@Id
	private String username;
	private String name;
	private String password;
	private String email;
	@OneToMany(mappedBy="user", cascade=CascadeType.DETACH, fetch=FetchType.EAGER, orphanRemoval=true)
	private List <Product> product = new ArrayList<Product>();

	public User() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List <Product> getProduct() {
		return product;
	}

	public void setProduct(List <Product> product) {
		this.product = product;
	}
	
}
