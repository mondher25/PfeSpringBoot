package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Utilisateur implements Serializable {
 
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String mdp;
	private String email;
	private String username;
	private Date dateInscrit;
	private boolean active;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	@JsonManagedReference
	private Collection<Role> roles;
	
	
 
	
	 
	
	
	 

}
