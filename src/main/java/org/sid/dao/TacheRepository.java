package org.sid.dao;

import java.util.List;

import org.sid.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TacheRepository extends JpaRepository<Tache, Long> {

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Non Commence'")
	int getTotalTacheNonCommence();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Annule'")
	int getTotalTacheAnnule();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Termine'")
	int getTotalTacheTermine();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='encours'")
	int getTotalTacheENcours();
	
	@Query("SELECT COUNT(t) FROM Tache t WHERE t.archive=false ")
	int totalTache();
	 
	@Query("SELECT COUNT(t) FROM Tache t WHERE t.archive=true ")
	int totalTacheArchived();
	
	List<Tache> findByUtilisateurId(Long id);
	
	@Query("SELECT COUNT(t) FROM Tache t WHERE t.utilisateur.id=:id")
	int findTotalByUtilisateurId(@Param("id") Long id);
	
	List<Tache> findByArchiveTrue();
	List<Tache> findByArchiveFalse();
	Tache findByNomTache(String nomTache);
	 
}

