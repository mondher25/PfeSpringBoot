package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.sid.entities.Utilisateur;
import org.sid.service.RoleService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtilisateurRestController {

	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private RoleService roleService;
	
 

	@PostMapping("/register")
	public Utilisateur register(@RequestBody Utilisateur utilisateur) {
		utilisateur.setDateInscrit(new Date());
		//profile.setUtilisateur(utilisateur);
		//profileRepository.save(profile);
		// utilisateurService.addRoleToUser(utilisateur.getUsername(), "ROLE_USER");
		utilisateur.setRoles(Arrays.asList(roleService.findByRole("ROLE_USER")));
		return utilisateurService.save(utilisateur);
	}

	@GetMapping("/users")
	public List<Utilisateur> findAll() {
		List<Utilisateur> listUsers = new ArrayList<>();
		listUsers = utilisateurService.findAll();
		return listUsers;
	}

	@GetMapping("/users/{id}")
	public Utilisateur findById(@PathVariable("id") Long id) {
		return utilisateurService.findOne(id);
	}
	
	@GetMapping("/users/AllByRole")
	public List<Utilisateur> findAllUserByRoleUser(){
		return utilisateurService.findAllUserByRole();
	}
	

}
