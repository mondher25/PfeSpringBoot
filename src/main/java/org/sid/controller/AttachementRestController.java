package org.sid.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.sid.controller.util.MediaTypeUtils;
import org.sid.entities.Attachement;
import org.sid.service.AttachementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class AttachementRestController {

	@Autowired
	private AttachementService attachementService;
	
	private static final String FILE_PATH = "D:\\pfe-workspace\\PFERestApi\\Attachement\\";
	List<String> files = new ArrayList<String>();
	@Autowired
	private ServletContext servletContext;
	
	@PostMapping("/attachements/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			attachementService.store(file);
			files.add(file.getOriginalFilename());
			message = "upload de fichier  " + file.getOriginalFilename() + "avec success !";
			Attachement attachement = new Attachement();
			attachement.setFile(file.getOriginalFilename());
			attachement.setDateCreation(new Date());
			//attachement.setTache(new Tache());
			attachementService.saveAttachement(attachement);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "erreur upload  " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping("/attachements/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(AttachementRestController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
 
		return ResponseEntity.ok().body(fileNames);
	}
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = attachementService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@PostMapping("/attachements")
	public Attachement  saveAttachement(@RequestBody Attachement attachement) {
		return attachementService.saveAttachement(attachement);
	}
	
	@GetMapping("/attachements")
	public List<Attachement> findAllAttachement() {
		return attachementService.findAllAttachement();
	}
	
	@DeleteMapping("/attachements/{id}")
	public void deleteAttachement(@PathVariable("id") Long id) {
		attachementService.deleteAttachementById(id);
	}
	@GetMapping("/attachements/{fileName:.+}")
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

}
