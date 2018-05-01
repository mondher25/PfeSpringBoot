package org.sid.service;

import java.util.List;

import org.sid.dao.EvenementRepository;
import org.sid.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvenementServiceImpl implements EvenementService {
	
	@Autowired
	private EvenementRepository evenementRepository;

	@Override
	public void save(Evenement evenement) {
		 evenementRepository.save(evenement);
		
	}
	
	public List<Evenement> findAll(){
		 
		return evenementRepository.findAll();
	}

}
