package org.sid.dao;

import org.sid.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {
 
}
