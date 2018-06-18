package org.sid.service;

import java.util.List;

import org.sid.entities.Tache;
import org.sid.entities.Utilisateur;

public interface TacheService {
	Tache save(Tache tache);
	List<Tache> findAll();
	void deleteById(Long id);
	int getTotalTacheENcours();
	int getTotalTacheNonCommence();
	int getTotalTacheAnnule();
	int getTotalTacheTermine();
	Tache findTacheById(Long id);
	int totalTache();
	int totalTacheArchived();
	List<Tache> findTacheByUtilisateurId(Utilisateur utilisateur);
	int findTotalByUtilisateurId(Utilisateur utilisateur);
	List<Tache> findByArchiveTrue();
	List<Tache> findByArchiveFalse();
	Tache findTachebyNomTache(String nomTache);
}
