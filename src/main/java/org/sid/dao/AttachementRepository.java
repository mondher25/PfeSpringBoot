package org.sid.dao;

import java.util.List;

import org.sid.entities.Attachement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachementRepository extends JpaRepository<Attachement, Long>{
	List<Attachement>  findByTacheId(Long id);
	
}
