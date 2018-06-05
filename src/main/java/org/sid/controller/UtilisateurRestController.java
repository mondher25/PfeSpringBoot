package org.sid.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.entities.Profile;
import org.sid.entities.Utilisateur;
import org.sid.service.ProfileService;
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
 	@Autowired
	private ProfileService profileService;

	@PostMapping("/register")
	public Utilisateur register(@RequestBody Utilisateur utilisateur) {
		utilisateur.setDateInscrit(new Date());
		// profile.setUtilisateur(utilisateur);
		// profileRepository.save(profile);
		// utilisateurService.addRoleToUser(utilisateur.getUsername(), "ROLE_USER");
		utilisateur.setRoles(Arrays.asList(roleService.findByRole("ROLE_USER")));
		Utilisateur user = utilisateurService.save(utilisateur);
		Profile profile = new Profile();
		profile.setUtilisateur(user);
		profileService.saveProfile(profile);
		return user;
	}

	@GetMapping("/users")
	public List<Utilisateur> findAll() {
		List<Utilisateur> listUsers = new ArrayList<>();
		listUsers = utilisateurService.findAll();
		return listUsers;
	}

	@GetMapping("/users/{id}")
	public Optional<Utilisateur> findById(@PathVariable("id") Long id) {
		return utilisateurService.getUserById(id);
	}

	@GetMapping("/users/roleUser")
	public List<Utilisateur> findAllUserByRoleUser() {
		return utilisateurService.findAllUserByRole();
	}

	@PostMapping("/users/manager")
	public Utilisateur addManager(@RequestBody Utilisateur manager) {
		manager.setDateInscrit(new Date());
		manager.setRoles(Arrays.asList(roleService.findByRole("ROLE_MANAGER")));
		manager.setActive(true);
		Utilisateur userManager = utilisateurService.save(manager);
		Profile profile = new Profile();
		profile.setUtilisateur(userManager);
		profileService.saveProfile(profile);
		return userManager;
	}

	@GetMapping("/users/roleManager")
	public List<Utilisateur> findAllUserByRoleManager() {
		return utilisateurService.findAllManagerRole();
	}

	@GetMapping("/users/statistique")
	public int TotalCompteNonActive() {
		return utilisateurService.totalCompteNonActive();
	}

	@GetMapping("/users/totale")
	public int totalCompte() {
		return utilisateurService.totalCompte();
	}

	@GetMapping("/users/JWT/{username}")
	public Utilisateur getUserByUsername(@PathVariable ("username") String username) {
		return utilisateurService.findUserByUsername(username);
	}
}
