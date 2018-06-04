package org.sid.service;

import java.util.List;

import org.sid.entities.Profile;

public interface ProfileService {
Profile saveProfile(Profile profile);
List<Profile> getAll();
}
