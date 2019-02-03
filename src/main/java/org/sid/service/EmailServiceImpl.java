package org.sid.service;

import java.util.Date;

import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void sendMailNotification(Utilisateur utilisateur) {
	 
		SimpleMailMessage mail  = new SimpleMailMessage(); 
		mail.setTo(utilisateur.getEmail());
		mail.setSentDate(new Date());
		mail.setText("votre compte à été activer vous pouvez accédez au platforme");
		mail.setSubject("Email de Notification d'activation de compte ");
		javaMailSender.send(mail);
	}

	@Override
	public void sendMailPassword(Utilisateur utilisateur) {
		SimpleMailMessage mail  = new SimpleMailMessage(); 
		mail.setTo(utilisateur.getEmail());
		mail.setSentDate(new Date());	
		mail.setText("votre mot de passe est " + utilisateur.getPassword());
		mail.setSubject("Email de Notification de recupération de mot de pass ");
		javaMailSender.send(mail);
		
	}
	

}
