package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Tache;

public interface TacheService {
	Tache save(Tache tache);
	List<Tache> findAll();
	void deleteById(Long id);
	int getTotalTacheENcours();
	int getTotalTacheNonCommence();
	int getTotalTacheAnnule();
	int getTotalTacheTermine();
	Optional<Tache> findTacheById(Long id);
	int totalTache();
}
