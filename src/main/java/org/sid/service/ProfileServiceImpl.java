package org.sid.service;

import org.sid.dao.ProfileRepository;
import org.sid.entities.Profile;
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

	 
}
