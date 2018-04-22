package org.sid.service;

import org.sid.entities.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
	Role findByRole(String role);

}
