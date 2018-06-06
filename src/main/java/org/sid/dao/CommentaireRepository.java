package org.sid.dao;

import java.util.List;

import org.sid.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

	 List<Commentaire> findByTacheId(Long id);
	 
}
