package org.sid.service;

import java.util.List;

import org.sid.entities.Evenement;
import org.sid.entities.Utilisateur;

public interface EvenementService {

	Evenement save(Evenement evenement);

	List<Evenement> findAll();

	void deleteById(Long id);

	void delete(Evenement evenement);

	Evenement findEventById(Long id);

	void updateEvent(Evenement evenement);

	void updateEvent(Long id, Evenement evenement);
	int totalEvent();
	int totalEventArchived();
	List<Evenement> findEventByUtilisateurId(Utilisateur utilisateur);
	
	 List<Evenement> findEventByArchiveTrue();
	 public List<Evenement> findEventByArchiveFalse();
	 
	 int findTotalByUtilisateurId(Utilisateur utilisateur);
}
