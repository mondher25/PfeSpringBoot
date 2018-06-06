package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
public class Attachement implements Serializable{


	private static final long serialVersionUID = 551449511222L;

	@Id @GeneratedValue
	private Long id;
	private String file;
	private Date dateCreation;	
	@ManyToOne(cascade =  CascadeType.ALL)
	private Tache tache;
	
	
}
