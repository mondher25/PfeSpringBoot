package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
public class Evenement implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	private String nomEvenement;
	private String lieu;
	@Temporal(TemporalType.DATE)
	private Date dateEvenement;
	private String heureEvenement;
	private String categorie;
	@Column(length=200)
	private String description;
	@OneToOne 	 
	private Utilisateur utilisateur;
	private Date dateCreation;
	private boolean archive;

}
