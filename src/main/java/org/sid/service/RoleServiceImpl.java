package org.sid.service;

import org.sid.dao.RoleRepository;
import org.sid.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByRole(String role) {
		 
		return roleRepository.findByRole(role);
	}

 
}
