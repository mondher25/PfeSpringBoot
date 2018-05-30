package org.sid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sid.dao.RoleRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entities.Role;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> list = new ArrayList<>();
		list = utilisateurRepository.findAll();
		return list.isEmpty() || list == null ? new ArrayList<>() : list;
	}

	@Override
	public Utilisateur save(Utilisateur utilisateur) {
		String hashPassword=bCryptPasswordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(hashPassword);
		return utilisateurRepository.save(utilisateur);

	}

	@Override
	public void update(Long id) {

	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public Optional<Utilisateur> getUserById(Long id) {

		return utilisateurRepository.findById(id);
	}

	@Override
	public Utilisateur findUserByUsername(String username) {
		return utilisateurRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String role) {
		Role roleUser=roleRepository.findByRole(role);
		Utilisateur utilisateur =utilisateurRepository.findByUsername(username);
		utilisateur.getRoles().add(roleUser);
		
	}

	@Override
	public List<Utilisateur> findAllUserByRole() {		 
		return utilisateurRepository.findAllUserByRole();
	}

	@Override
	public List<Utilisateur> findAllManagerRole() {
		 
		return utilisateurRepository.findAllManagerRole();
	}

	@Override
	public int totalCompteNonActive() {
		 
		return utilisateurRepository.totalCompteNonActive();
	}

}
