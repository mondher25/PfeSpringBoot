package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Document implements Serializable {

	private static final long serialVersionUID = 25516584984814L;
	@Id
	@GeneratedValue
	private Long id;
	private String nomDocument;
	private String typeDocument;
	@Column(length = 200)
	private String description;
	private String file;
	private Date dateCreation;
	private boolean etat;
	private boolean archive;

	@OneToOne
	private Utilisateur utilisateur;

}
