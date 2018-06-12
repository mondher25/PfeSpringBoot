package org.sid.service;

import java.util.List;

import org.sid.entities.Profile;
import org.sid.entities.Utilisateur;

public interface ProfileService {
Profile saveProfile(Profile profile);
List<Profile> getAll();
public Profile findProfileByUser(Utilisateur utilisateur);
Profile getProfileById(Long id);
}
