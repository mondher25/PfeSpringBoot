package org.sid.service;

import java.util.List;

import org.sid.entities.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> findAll();
	void save(Utilisateur utilisateur);
	void update(Long id);
	void delete(Long id);
	Utilisateur findOne(Long id);

}
