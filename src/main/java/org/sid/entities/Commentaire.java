package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Commentaire implements Serializable {
 
	private static final long serialVersionUID = 2561149894191L;
	@Id @GeneratedValue
	private Long id;
	@Column(length=250)
	private String comment;
	private Date dateComment;
	@ManyToOne
	private Tache tache; 
	@ManyToOne
	private Utilisateur utilisateur;

}
