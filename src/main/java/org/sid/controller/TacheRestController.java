package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.entities.Commentaire;
import org.sid.entities.Tache;
import org.sid.service.CommentaireService;
import org.sid.service.TacheService;
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
public class TacheRestController {

	@Autowired
	private TacheService tacheService;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private CommentaireService commentaireService;

	@PostMapping("/tasks")
	public Tache saveTache(@RequestBody Tache tache) {

		tache.setDateAffectation(new Date());
		return tacheService.save(tache);

	}

	@PutMapping("/tasks/{id}")
	public Tache updateTache(@PathVariable Long id, @RequestBody Tache task) {
		task.setId(id);
		System.out.println("update Tache --- ");
		System.out.println(task.getEtatTache());
		return tacheService.save(task);
	}

	@GetMapping("/tasks")
	public List<Tache> findAllTache() {
		return tacheService.findByArchiveFalse();

	}

	@DeleteMapping("/tasks/{id}")
	public void deleteTache(@PathVariable("id") Long id) {
		tacheService.deleteById(id);
	}

	@GetMapping("/tasks/encours")
	public int getTotalTacheENcours() {
		return tacheService.getTotalTacheENcours();
	}

	@GetMapping("/tasks/termine")
	public int getTotalTacheTermine() {
		return tacheService.getTotalTacheTermine();
	}

	@GetMapping("/tasks/nonCommence")
	public int getTotalTacheNonCommence() {
		return tacheService.getTotalTacheNonCommence();
	}

	@GetMapping("/tasks/annule")
	public int getTotalTacheAnnule() {
		return tacheService.getTotalTacheAnnule();
	}

	@GetMapping("/tasks/{id}")
	public Optional<Tache> findTacheById(@PathVariable("id") Long id) {
		return tacheService.findTacheById(id);
	}

	@PostMapping("/tasks/comment")
	public Commentaire saveComment(@RequestBody Commentaire commentaire) {
		String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentaire.setUtilisateur(utilisateurService.findUserByUsername(user));
		commentaire.setDateComment(new Date());
		return commentaireService.saveComment(commentaire);
	}

	@GetMapping("/tasks/total")
	public int totalTache() {
		return tacheService.totalTache();
	}

	@GetMapping("/tasks/total/archive")
	public int totalTacheArchived() {
		return tacheService.totalTacheArchived();
	}

	@GetMapping("/tasks/Auth/{username}")
	public List<Tache> getTacheByUser(@PathVariable("username") String username) {

		return tacheService.findTacheByUtilisateurId(utilisateurService.findUserByUsername(username));
	}

	@GetMapping("/tasks/comment/{id}")
	public List<Commentaire> getCommentByTache(@PathVariable("id") Long id) {

		return commentaireService.findCommentaireByTacheId(id);
	}

	@GetMapping("/tasks/total/{username}")
	public int totalTacheByUsername(@PathVariable("username") String username) {
		return tacheService.findTotalByUtilisateurId(utilisateurService.findUserByUsername(username));
	}

	@GetMapping("/tasks/archives")
	public List<Tache> getArchivedTache() {
		return tacheService.findByArchiveTrue();
	}

	@PutMapping("/tasks/archive/{id}")
	public void archiveTask(@PathVariable Long id, @RequestBody Tache task) {
		task.setId(id);
		task.setArchive(true);
		System.out.println("task archived !!!");
		tacheService.save(task);
	}

}
