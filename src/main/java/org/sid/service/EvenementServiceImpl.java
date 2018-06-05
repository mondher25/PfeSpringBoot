package org.sid.service;

import java.util.List;

import org.sid.dao.EvenementRepository;
import org.sid.entities.Evenement;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvenementServiceImpl implements EvenementService {

	@Autowired
	private EvenementRepository evenementRepository;

	@Override
	public Evenement save(Evenement evenement) {
		if (!evenement.isArchive())
			evenement.setArchive(false);
		return evenementRepository.save(evenement);

	}

	public List<Evenement> findAll() {

		return evenementRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {

		evenementRepository.deleteById(id);
	}

	@Override
	public Evenement findEventById(Long id) {
		return evenementRepository.findEventById(id);
	}

	@Override
	public void delete(Evenement evenement) {
		evenementRepository.delete(evenement);

	}

	@Override
	public void updateEvent(Evenement evenement) {
		Evenement event = findEventById(evenement.getId());
		event.setCategorie(evenement.getCategorie());
		event.setDateEvenement(evenement.getDateEvenement());
		event.setHeureEvenement(evenement.getHeureEvenement());
		event.setDescription(evenement.getDescription());
		event.setLieu(evenement.getLieu());
		event.setNomEvenement(evenement.getNomEvenement());
		evenementRepository.save(event);

	}

	@Override
	public void updateEvent(Long id, Evenement evenement) {
		Evenement event = findEventById(id);
		event.setCategorie(evenement.getCategorie());
		event.setDateEvenement(evenement.getDateEvenement());
		event.setHeureEvenement(evenement.getHeureEvenement());
		event.setDescription(evenement.getDescription());
		event.setLieu(evenement.getLieu());
		event.setNomEvenement(evenement.getNomEvenement());
		evenementRepository.save(event);

	}

	@Override
	public int totalEvent() {

		return evenementRepository.totalEvent();
	}

	@Override
	public List<Evenement> findEventByUtilisateurId(Utilisateur utilisateur) {

		return evenementRepository.findByUtilisateurIdAndArchiveFalse(utilisateur.getId());
	}

	@Override
	public List<Evenement> findEventByArchiveTrue() {

		return evenementRepository.findByArchiveTrue();
	}

	@Override
	public List<Evenement> findEventByArchiveFalse() {

		return evenementRepository.findByArchiveFalse();
	}

}
