package org.sid.controller;

import java.util.Date;
import java.util.List;

import org.sid.entities.Profile;
import org.sid.entities.Utilisateur;
import org.sid.service.ProfileService;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileRestController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/profiles")
	public List<Profile> getAllProfile() {
		return profileService.getAll();

	}

	@PutMapping("/profiles/compte/{id}")
	public Utilisateur editCompte(@PathVariable Long id, @RequestBody Utilisateur profile) {
		/*
		 * Utilisateur user =UtilisateurRepository.getOne(id);
		 * user.setNom(profile.getNom()); user.setPrenom(profile.getPrenom());
		 * user.setEmail(profile.getEmail()); String
		 * hashPassword=bCryptPasswordEncoder.encode(profile.getPassword());
		 * profile.setPassword(hashPassword);
		 */
		profile.setId(id);
		return utilisateurService.save(profile);

	}

	@PutMapping("/profiles/info/{id}")
	public Profile editCompte(@PathVariable Long id, @RequestBody Profile profile) {
		profile.setId(id);
		return profileService.saveProfile(profile);

	}

	@PutMapping("/profiles/password/{id}")
	public Utilisateur editPassword(@PathVariable Long id, @RequestBody Utilisateur profile) {
		profile.setId(id);
		String hashPassword = bCryptPasswordEncoder.encode(profile.getPassword());
		profile.setPassword(hashPassword);
		return utilisateurService.save(profile);

	}

	@GetMapping("/profiles/JWT/{username}")
	public Profile getProfileByUsername(@PathVariable("username") String username) {

		return profileService.findProfileByUser(utilisateurService.findUserByUsername(username));
	}

	@PutMapping("/profiles/connexion/{username}")
	public Profile updateDernierConnexion(@PathVariable("username") String username) {
		// String user =(String)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Profile profile = profileService
				.findProfileByUser(utilisateurService.findUserByUsername(username));
		System.out.println("--------------------------------------------");
		System.out.println(profile.getUtilisateur().getEmail());
		profile.setDerniereConnexion(new Date());
		
		return profileService.saveProfile(profile);
	}
	
	@GetMapping("/profiles/detail/{id}")
	public Profile getDetailProfile(@PathVariable("id") Long id) {
		
		return profileService.getProfileById(id);
	}
}
