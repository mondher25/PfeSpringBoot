package org.sid.service;

import java.util.List;

import org.sid.entities.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
	public void store(MultipartFile file);

	Document save(Document document);

	List<Document> findAll();

	int totalDocument();
	int totalDocumentArchived();

	List<Document> findDocumentByArchiveTrue();

	List<Document> findDocumentByArchiveFalseEtatPublic();

	List<Document> findDocumentByArchiveFalseEtatPrive();

	Document updateDocument(Document document);
	}
