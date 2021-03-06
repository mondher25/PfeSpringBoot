package org.sid.service;

import java.util.List;

import org.sid.dao.AttachementRepository;
import org.sid.dao.CommentaireRepository;
import org.sid.dao.TacheRepository;
import org.sid.entities.Attachement;
import org.sid.entities.Commentaire;
import org.sid.entities.Tache;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacheServiceImpl implements TacheService {

	@Autowired
	private TacheRepository tacheRepository;
	
	@Autowired
	private AttachementRepository attachementRepository;
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Override
	public Tache save(Tache tache) {
		if (tache.getEtatTache() == null)
		tache.setEtatTache("Non Commencer");
		if (!tache.isArchive())
		tache.setArchive(false);
		return tacheRepository.save(tache);
	}

	@Override
	public List<Tache> findAll() {

		return tacheRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		System.out.println("id tache" + id);
		List<Commentaire> listeComment=  commentaireRepository.findByTacheId(id);
		List<Attachement> listeTache = attachementRepository.findByTacheId(id);
		listeTache.forEach(a ->attachementRepository.deleteById(a.getId()) );
		 
		for (Commentaire c:listeComment ) {
			commentaireRepository.deleteById(c.getId());
		}
		
		tacheRepository.deleteById(id);

	}

	@Override
	public int getTotalTacheENcours() {

		return tacheRepository.getTotalTacheENcours();
	}

	@Override
	public int getTotalTacheNonCommence() {

		return tacheRepository.getTotalTacheNonCommence();
	}

	@Override
	public int getTotalTacheAnnule() {

		return tacheRepository.getTotalTacheAnnule();
	}

	@Override
	public int getTotalTacheTermine() {

		return tacheRepository.getTotalTacheTermine();
	}

	@Override
	public Tache findTacheById(Long id) {
		 
		return tacheRepository.getOne(id);
		 
	}

	@Override
	public int totalTache() {
		return tacheRepository.totalTache();
	}

	@Override
	public List<Tache> findTacheByUtilisateurId(Utilisateur utilisateur) {
		 
		return tacheRepository.findByUtilisateurIdAndArchiveFalse(utilisateur.getId());
	}

	@Override
	public int findTotalByUtilisateurId(Utilisateur utilisateur) {
		 
		return tacheRepository.findTotalByUtilisateurId(utilisateur.getId());
	}

	@Override
	public List<Tache> findByArchiveTrue() {
		 
		return tacheRepository.findByArchiveTrue();
	}

	@Override
	public List<Tache> findByArchiveFalse() {
		 
		return tacheRepository.findByArchiveFalse();
	}

	@Override
	public int totalTacheArchived() {
		 
		return tacheRepository.totalTacheArchived();
	}

	@Override
	public Tache findTachebyNomTache(String nomTache) {
 
		return tacheRepository.findByNomTache(nomTache);
	}

}
