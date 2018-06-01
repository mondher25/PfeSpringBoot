package org.sid.controller;

import java.util.Date;
import java.util.List;

import org.sid.entities.Evenement;
import org.sid.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/events")
	public Evenement save(@RequestBody Evenement evenement) {
		evenement.setDateCreation(new Date());
		return evenementService.save(evenement);
	}

	@GetMapping("/events")
	public List<Evenement> findAll() {
		return evenementService.findAll();
	}

	@DeleteMapping("/events/{id}")
	public void deleteEvenent(@PathVariable("id") Long id) {
		evenementService.deleteById(id);
	}

	@PutMapping("/events")
	public void updateEvent( @RequestBody Evenement evenement) {
		evenementService.save(evenement);
	}

	@GetMapping("/events/{id}")
	public Evenement findEventById(@PathVariable("id") Long id) {
		return evenementService.findEventById(id);

	}
	
	@GetMapping("/events/totale")
	public int totalEvent() {
		return evenementService.totalEvent();
	}

}
