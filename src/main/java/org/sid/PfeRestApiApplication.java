package org.sid;

import java.util.List;

import org.sid.entities.Utilisateur;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfeRestApiApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;
 
	
 

	public static void main(String[] args) {
		SpringApplication.run(PfeRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Utilisateur admin = new Utilisateur(1L, "Guemri", "Mondher", "admin", "mondher.guemri.dev@gmail.com", "admin",
				new Date(), true, Arrays.asList(roleService.findByRole("ROLE_ADMIN")));
		Profile profile =new Profile(1L, admin);

		Role role_admin = new Role(1L, "ROLE_ADMIN");
		roleService.saveRole(role_admin);
		Role role_user = new Role(2L, "ROLE_USER");
		roleService.saveRole(role_user);
		Role role_manager = new Role(3L, "ROLE_MANAGER");
		roleService.saveRole(role_manager);
		utilisateurService.save(admin);
		profileService.saveProfile(profile);*/
		List<Utilisateur> list = utilisateurService.findAllUserByRole();
		list.forEach(u -> System.out.println(u.getNom()));

	}
}
