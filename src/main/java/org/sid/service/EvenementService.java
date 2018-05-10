package org.sid.service;

import java.util.List;

import org.sid.entities.Evenement;

public interface EvenementService {

	Evenement save(Evenement evenement);

	List<Evenement> findAll();

	void deleteById(Long id);

	void delete(Evenement evenement);

	Evenement findEventById(Long id);

	void updateEvent(Evenement evenement);
	void updateEvent(Long id,Evenement evenement);
}
