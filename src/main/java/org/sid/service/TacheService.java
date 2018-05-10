package org.sid.service;

import java.util.List;

import org.sid.entities.Tache;

public interface TacheService {
	Tache save(Tache tache);
	List<Tache> findAll();
	void deleteById(Long id);
	
}
