package org.sid.service;

import java.util.List;

import org.sid.entities.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> findAll();
	Utilisateur save(Utilisateur utilisateur);
	List<Utilisateur> findAllUserByRole();
	void update(Long id);
	void delete(Long id);
	Utilisateur findOne(Long id);
	Utilisateur findUserByUsername(String username);
	void addRoleToUser(String usename,String role);
}
