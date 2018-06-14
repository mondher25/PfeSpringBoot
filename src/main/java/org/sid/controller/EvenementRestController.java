package org.sid.controller;

import java.util.Date;
import java.util.List;

import org.sid.entities.Evenement;
import org.sid.entities.Profile;
import org.sid.service.EvenementService;
import org.sid.service.ProfileService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EvenementRestController {

	@Autowired
	private EvenementService evenementService;

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/events")
	public Evenement save(@RequestBody Evenement evenement) {
		evenement.setDateCreation(new Date());
		evenement.setEtatEvent("EN ATTENTE");		 
		String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		evenement.setUtilisateur(utilisateurService.findUserByUsername(user));
		return evenementService.save(evenement);
	}

	@GetMapping("/events")
	public List<Evenement> findAll() {
		return evenementService.findEventByArchiveFalse();
	}

	@DeleteMapping("/events/{id}")
	public void deleteEvenent(@PathVariable("id") Long id) {
		evenementService.deleteById(id);
	}

	@PutMapping("/events/{id}")
	public void updateEvent(@PathVariable Long id, @RequestBody Evenement event) {
		event.setId(id);
		if (event.getDateEvenement() == null ) {
			event.setEtatEvent("EN ATTENTE");
		}
		evenementService.save(event);
	}

	@GetMapping("/events/{id}")
	public Evenement findEventById(@PathVariable("id") Long id) {
		return evenementService.findEventById(id);

	}
	
	@GetMapping("/events/totale")
	public int totalEvent() {
		return evenementService.totalEvent();
	}
	@GetMapping("/events/totale/archive")
	public int totalEventArchived() {
		return evenementService.totalEventArchived();
	}
	@GetMapping("/events/Auth/{username}")
	public List<Evenement> getEventByUser(@PathVariable("username") String username) {
		 
		return evenementService.findEventByUtilisateurId(utilisateurService.findUserByUsername(username));
	}
	
	@PutMapping("/events/archive/{id}")
	public void archiveEvent(@PathVariable Long id, @RequestBody Evenement event) {
		event.setId(id);
		event.setArchive(true);
		 System.out.println("event archived !!!");
		evenementService.save(event);
	}
	
	@GetMapping("/events/archives")
	public List<Evenement> getAllEventArchived(){
		return evenementService.findEventByArchiveTrue();
	}
	@GetMapping("/events/total/{username}")
	public int totalTacheByUsername(@PathVariable("username") String username) {
		return evenementService.findTotalByUtilisateurId(utilisateurService.findUserByUsername(username));
	}
	@GetMapping("/events/contact/{id}")
	public List<Evenement> getContactEvent(@PathVariable("id") Long	id) {

		 Profile profile =profileService.getProfileById(id);
		 return evenementService.findEventByUtilisateurId(profile.getUtilisateur());
	}
	

}
