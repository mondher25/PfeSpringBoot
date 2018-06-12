package org.sid.service;

import java.util.List;

import org.sid.dao.ProfileRepository;
import org.sid.entities.Profile;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository ProfileRepository;
	 
	@Override
	public Profile saveProfile(Profile profile) {

		return ProfileRepository.saveAndFlush(profile);
	}

	@Override
	public List<Profile> getAll() {

		return ProfileRepository.findAll();
	}

	@Override
	public Profile findProfileByUser(Utilisateur utilisateur) {

		return ProfileRepository.findByUtilisateurId(utilisateur.getId());
	}

	@Override
	public Profile getProfileById(Long id) {
		 
		return ProfileRepository.getOne(id);
	}

}
