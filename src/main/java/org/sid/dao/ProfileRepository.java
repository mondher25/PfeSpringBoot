package org.sid.dao;

import org.sid.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findByUtilisateurId(Long id);
}
