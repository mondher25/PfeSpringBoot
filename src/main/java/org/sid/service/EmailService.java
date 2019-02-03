package org.sid.service;

import org.sid.entities.Utilisateur;

public interface EmailService {
	void sendMailNotification (Utilisateur utilisateur);
	void sendMailPassword(Utilisateur utilisateur);
}
