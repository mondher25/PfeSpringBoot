package org.sid;

import java.util.List;

import org.sid.entities.Utilisateur;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfeRestApiApplication implements CommandLineRunner  {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	public static void main(String[] args) {
		SpringApplication.run(PfeRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 List<Utilisateur> list= utilisateurService.findAllUserByRole();
	 list.forEach(u-> System.out.println(u.getNom()));
		
	}
}
