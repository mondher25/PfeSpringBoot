package org.sid.service;

import java.util.List;

import org.sid.entities.Attachement;
import org.sid.entities.Tache;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AttachementService {
	void store(MultipartFile file);

	Resource loadFile(String filename);

	Attachement saveAttachement(Attachement attachement);

	List<Attachement> findAttachementByTacheId(Tache tache);

	List<Attachement> findAllAttachement();

	void deleteAttachementById(Long id);

}
