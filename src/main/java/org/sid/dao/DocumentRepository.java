package org.sid.dao;

import java.util.List;

import org.sid.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	@Query("SELECT COUNT(d) FROM Document d Where d.archive=false ")
	int totalDocument();
	
	@Query("SELECT COUNT(d) FROM Document d Where d.archive=true ")
	int totalDocumentArchived();

	List<Document> findByArchiveTrue();

	List<Document> findByArchiveFalseAndEtatTrue();
	List<Document> findByArchiveFalseAndEtatFalse();
	
	@Query("SELECT d FROM Document d Where d.archive=false AND d.etat=false AND d.utilisateur.id=:id")
	List<Document> findDocumentByUtilisateurIdAndEtatFalseAndArchiveFalse(@Param("id") Long id);
	
	List<Document> findByUtilisateurIdAndArchiveFalse(Long id);
}
