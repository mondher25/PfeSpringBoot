package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Profile implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String address;
	private String numeroTelephone;
	private String profession;
	private String photoProfile;
	@OneToOne  
	private Utilisateur utilisateur;
	public Profile(Long id, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
	}
	

	 
}
