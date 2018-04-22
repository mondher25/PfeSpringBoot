package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.sid.dao.RoleRepository;
import org.sid.entities.Utilisateur;
import org.sid.service.UtilisateurService;
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
public class UtilisateurController {
	
	 
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/users")
	public void save(@RequestBody Utilisateur utilisateur) {
		utilisateur.setDateInscrit(new Date());
		utilisateur.setRoles(Arrays.asList(roleRepository.findByRole("ROLE_USER")));
		utilisateurService.save(utilisateur);
	}
	
	@GetMapping("/users")
	public List<Utilisateur> findAll() {
		List<Utilisateur> listUsers = new ArrayList<>();
		listUsers=utilisateurService.findAll();
		return listUsers;
	}
 
	
	

}
