package org.sid.dao;

import org.sid.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

	@Query("SELECT e FROM Evenement e Where id=:id")
	Evenement findEventById(@Param("id") Long id);

}
