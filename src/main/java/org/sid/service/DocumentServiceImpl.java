package org.sid.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.sid.dao.DocumentRepository;
import org.sid.entities.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements DocumentService {
	private final Path rootLocation = Paths.get("Document");

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public void store(MultipartFile file) {
		try {
			System.out.println(file.getOriginalFilename());
			System.out.println(rootLocation.toUri());
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}

	}

	@Override
	public Document save(Document document) {
		int length = document.getFile().length();
		String path = document.getFile().substring(12, length);		
		document.setArchive(false);
		document.setFile(path);
		return documentRepository.save(document);

	}
	
	public Document updateDocument(Document document) {
		return documentRepository.saveAndFlush(document);
	}

	@Override
	public List<Document> findAll() {
		 		return documentRepository.findAll();
	}

	@Override
	public int totalDocument() {
		 
		return documentRepository.totalDocument();
	}

	@Override
	public List<Document> findDocumentByArchiveTrue() {
		 
		return documentRepository.findByArchiveTrue();
	}

	@Override
	public List<Document> findDocumentByArchiveFalseEtatPublic() {
		 
		return documentRepository.findByArchiveFalseAndEtatTrue();
	}

	@Override
	public List<Document> findDocumentByArchiveFalseEtatPrive() {
		 
		return documentRepository.findByArchiveFalseAndEtatFalse();
	}

	@Override
	public int totalDocumentArchived() {
	 
		return documentRepository.totalDocumentArchived();
	}

	@Override
	public void deleteDocument(Long id) {
		documentRepository.deleteById(id);
		
	}

	public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.rootLocation.resolve(fileName).normalize();
	            System.out.println("filePath : Service"+ filePath);
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	            	System.out.println("resource : Service"+ resource);
	                return resource;
	            } else {
	                throw new RuntimeException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new RuntimeException("File not found " + fileName, ex);
	        }
	    }

}
