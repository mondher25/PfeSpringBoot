package org.sid.dao;

import org.sid.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TacheRepository extends JpaRepository<Tache, Long> {

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Non Commence'")
	int getTotalTacheNonCommence();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Annule'")
	int getTotalTacheAnnule();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='Termine'")
	int getTotalTacheTermine();

	@Query("SELECT COUNT(t.etatTache) FROM Tache t WHERE t.etatTache ='encours'")
	int getTotalTacheENcours();
	
	@Query("SELECT COUNT(t) FROM Tache t ")
	int totalTache();
	 
}

