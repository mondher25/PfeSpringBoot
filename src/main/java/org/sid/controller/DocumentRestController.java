package org.sid.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.sid.controller.util.MediaTypeUtils;
import org.sid.entities.Document;
import org.sid.entities.Utilisateur;
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
	 private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private ServletContext servletContext;

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
		String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		document.setUtilisateur(utilisateurService.findUserByUsername(user));
		document.setDateCreation(new Date());
		return documentService.save(document);
	}

	@GetMapping("/documents/public")
	public List<Document> getAll() {
		return documentService.findDocumentByArchiveFalseEtatPublic();
	}

	@GetMapping("/documents/edit/{id}")
	public Document getDocumentById(@PathVariable("id")Long id) {
		return documentService.getDocumentById(id);
	}
	@GetMapping("/documents/prive")
	public List<Document> getAllDocumentPrivate() {
		return documentService.findDocumentByArchiveFalseEtatPrive();
	}

	@GetMapping("/documents/{fileName:.+}")
	public ResponseEntity<InputStreamResource> handleFileDownload(@PathVariable(value="fileName") String fileName, HttpServletResponse resonse) throws IOException {
		//Resource resource = documentService.loadFileAsResource(fileName);
		 MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
		  
	   		// Try to determine file's content type
		 
		 File file = new File(FILE_PATH + "/" + fileName);
	        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));	        
		System.out.println("+------------------------------------+");
		System.out.println(fileName);		
		return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(inputStreamResource);

	}


	public ResponseEntity<InputStreamResource> download(@RequestParam(value="fileName", defaultValue=DEFAULT_FILE_NAME) String fileName) throws FileNotFoundException {
		String fullPath = FILE_PATH + fileName;
		File file = new File(fullPath);		
		HttpHeaders httpHeaders = new HttpHeaders();	 
	 
		httpHeaders.setContentType(MediaType.APPLICATION_PDF);
		httpHeaders.add("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(inputStreamResource,httpHeaders,HttpStatus.OK);
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
	public List<Document> getAllArchivedDocument() {
		return documentService.findDocumentByArchiveTrue();
	}

	@DeleteMapping("/documents/{id}")
	public void deleteArchivedDocument(@PathVariable("id") Long id) {
		documentService.deleteDocument(id);

	}

	@PutMapping("/documents/archive/{id}")
	public Document archiveDocument(@PathVariable("id") Long id, @RequestBody Document document) {
		document.setId(id);
		document.setArchive(true);
		return documentService.updateDocument(document);
	}

	@PutMapping("/documents/restaurer/{id}")
	public Document restaurerDocument(@PathVariable("id") Long id, @RequestBody Document document) {
		document.setId(id);
		document.setArchive(false);
		return documentService.updateDocument(document);
	}
	@GetMapping("/documents/prive/username/{username}")
	public List<Document> getDocumentPriveByUsername(@PathVariable("username") String username) {
		
		Utilisateur utilisateur = utilisateurService.findUserByUsername(username);
		return documentService.findDocumentByUsername(utilisateur);
	}
	
	@PutMapping("/documents/{id}")
	public Document updateDocument(@PathVariable ("id") Long id,@RequestBody Document document) {
		document.setId(id);
		return documentService.updateDocument(document);
	}
	@GetMapping("/documents/Auth/{username}")
	public List<Document> getDocByUser(@PathVariable("username") String username) {
		 
		return documentService.findByUtilisateur(utilisateurService.findUserByUsername(username));
	}
}
