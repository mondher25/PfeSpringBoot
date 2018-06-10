package org.sid.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.sid.entities.Document;
import org.sid.service.DocumentService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DocumentRestController {

	@Autowired
	private DocumentService documentService;
	private static final String FILE_PATH = "D:\\pfe-workspace\\PFERestApi\\Document\\";
	
	@Autowired
	private UtilisateurService utilisateurService ;

	@PostMapping("/document/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			documentService.store(file);

			message = "upload de fichier  " + file.getOriginalFilename() + "avec success !";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "erreur upload  " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping("/documents")
	public Document save(@RequestBody Document document) {
		String user =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		document.setUtilisateur(utilisateurService.findUserByUsername(user));
		document.setDateCreation(new Date());
		return documentService.save(document);
	}

	@GetMapping("/documents/public")
	public List<Document> getAll() {
		return documentService.findDocumentByArchiveFalseEtatPublic();
	}
	@GetMapping("/documents/prive")
	public List<Document> getAllDocumentPrivate() {
		return documentService.findDocumentByArchiveFalseEtatPrive();
	}

	@GetMapping("/documents/{fileName:.+}")
	public ResponseEntity<InputStreamResource> handleFileDownload(@PathVariable("fileName") String fileName) {
		String fullPath = FILE_PATH + "" + fileName;
		@SuppressWarnings("unused")
		String message = "";
		try {
			System.out.println(fullPath);
			File file = new File(fullPath);
			InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
			message = "Telechargement de fichier  " + fileName + "avec success !";
			 HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.parseMediaType("application/pdf")); 
			  headers.add("Content-Disposition", "filename=" + fileName);
			  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			  headers.add("Pragma", "no-cache");
			  headers.add("Expires", "0");
			return new ResponseEntity<InputStreamResource>(inputStreamResource, headers, HttpStatus.OK);

		} catch (Exception e) {

			message = "erreur detelechargement de fichier  " + fileName + "!";
			return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@GetMapping("/documents/totale")
	public int totalDocument() {
		return documentService.totalDocument();
	}
	
	@GetMapping("/documents/totale/archive")
	public int totalDocumentArchived() {
		return documentService.totalDocumentArchived();
	}

	@GetMapping("/documents/archives")
	public List<Document> getAllArchivedDocument(){
		return documentService.findDocumentByArchiveTrue();
	}
	
	@DeleteMapping("/documents/{id}")
	public void deleteArchivedDocument(@PathVariable("id") Long id) {
		documentService.deleteDocument(id);
		
	}
	
	@PutMapping("/documents/archive/{id}")
	public Document archiveDocument(@PathVariable("id")Long id,@RequestBody Document document) {
		document.setId(id);
		document.setArchive(true);
		return documentService.updateDocument(document);
	}
	@PutMapping("/documents/restaurer/{id}")
	public Document restaurerDocument(@PathVariable("id")Long id,@RequestBody Document document) {
		document.setId(id);
		document.setArchive(false);
		return documentService.updateDocument(document);
	}
}
