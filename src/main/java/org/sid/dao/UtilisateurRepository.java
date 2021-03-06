package org.sid.dao;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

 public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

	 Utilisateur findByUsername(String username);
	
	@Query("SELECT u FROM Utilisateur u JOIN u.roles r where r.role ='ROLE_USER'")
	List<Utilisateur> findAllUserByRole();
	
	@Query("SELECT u FROM Utilisateur u JOIN u.roles r where r.role ='ROLE_MANAGER'")
	List<Utilisateur> findAllManagerRole();
	
	@Query("select count(u) from Utilisateur u where u.active = false ")
	int totalCompteNonActive();
	
	@Query("SELECT COUNT(u) FROM Utilisateur u ")
	int totalCompte();
	
	Optional<Utilisateur> findById(Long id);
	Utilisateur findByEmail(String email);
 }
