package org.sid.dao;

import java.util.List;

import org.sid.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

	@Query("SELECT e FROM Evenement e Where id=:id")
	Evenement findEventById(@Param("id") Long id);

	@Query("SELECT COUNT(e) FROM Evenement e WHERE e.archive=false ")
	int totalEvent();

	@Query("SELECT COUNT(e) FROM Evenement e WHERE e.archive=true ")
	int totalEventArchived();

	@Query("SELECT COUNT(e) FROM Evenement e WHERE e.utilisateur.id=:id")
	int findTotalByUtilisateurId(@Param("id") Long id);

	List<Evenement> findByUtilisateurIdAndArchiveFalse(Long id);

	List<Evenement> findByArchiveTrue();

	List<Evenement> findByArchiveFalse();
}
