package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.sid.entities.Profile;
import org.sid.entities.Utilisateur;
import org.sid.service.RoleService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private RoleService roleService;
	
	private Profile profile =new Profile();
	
	@PostMapping("/users")
	public void save(@RequestBody Utilisateur utilisateur) {
		utilisateur.setDateInscrit(new Date());
		profile.setUtilisateur(utilisateur);
 		utilisateur.setRoles(Arrays.asList(roleService.findByRole("ROLE_USER")));
		utilisateurService.save(utilisateur);
	}
	
	@GetMapping("/users")
	public List<Utilisateur> findAll() {
		List<Utilisateur> listUsers = new ArrayList<>();
		listUsers=utilisateurService.findAll();
		return listUsers;
	}
	
	@GetMapping("/user/{id}")
	public Utilisateur findById(@PathVariable ("id") Long id) {			
		return utilisateurService.findOne(id);
	}
 
	
	

}
