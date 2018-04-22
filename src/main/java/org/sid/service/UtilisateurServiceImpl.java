package org.sid.service;

import java.util.ArrayList;
import java.util.List;

import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> list =new ArrayList<>();		 
		list=utilisateurRepository.findAll();
		return list.isEmpty() || list ==null ? new ArrayList<>() : list;
	}

	@Override
	public void save(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
		
	}

	@Override
	public void update(Long id) {
		 
		
	}

	@Override
	public void delete(Long id) {
	 
	}

	@Override
	public Utilisateur findOne(Long id) {
		 
		return null;
	}

}
