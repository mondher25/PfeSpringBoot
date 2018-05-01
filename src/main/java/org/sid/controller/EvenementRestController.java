package org.sid.controller;

import java.util.List;

import org.sid.entities.Evenement;
import org.sid.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EvenementRestController {
	
	@Autowired
	private EvenementService evenementService;
	
	@PostMapping("/events")
	public void save(@RequestBody Evenement evenement) {
		
		evenementService.save(evenement);
	}
	
	@GetMapping("/events")
	public List<Evenement> findAll(){
		return evenementService.findAll();
	}

}
