package org.sid.dao;

import org.sid.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
