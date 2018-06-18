package org.sid.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.sid.dao.AttachementRepository;
import org.sid.entities.Attachement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachementServiceImpl implements AttachementService {
	
	@Autowired
	private AttachementRepository attachementRepository;
	private final Path rootLocation = Paths.get("Attachement");
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
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	@Override
	public Attachement saveAttachement(Attachement attachement) {
		
		return attachementRepository.save(attachement);
	}
	@Override
	public List<Attachement> findAttachementByTacheId(Long id) {
		 
		return attachementRepository.findByTacheId(id);
	}
	@Override
	public List<Attachement> findAllAttachement() {
		 
		return attachementRepository.findAll();
	}
	@Override
	public void deleteAttachementById(Long id) {
		attachementRepository.deleteById(id);
		
	}

}
