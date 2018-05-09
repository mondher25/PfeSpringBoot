package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 665659898877521L;
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String password;
	private String email;
	@Column(unique = true)
	private String username;
	private Date dateInscrit;
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private List<Role> roles = new ArrayList<Role>();
	
 

	
	
	//Getter AND Setter
	 

 
	public Long getId() {
		return id;
	}

	@JsonGetter
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateInscrit() {
		return dateInscrit;
	}

	public void setDateInscrit(Date dateInscrit) {
		this.dateInscrit = dateInscrit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
 
	public List<Role> getRoles() {
		return roles;
	}

 
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

 
}
