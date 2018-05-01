package org.sid.service;

import java.util.List;

import org.sid.entities.Evenement;


public interface EvenementService {
 
	  void save(Evenement evenement);
	  List<Evenement> findAll();
}
