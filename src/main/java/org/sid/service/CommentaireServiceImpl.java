package org.sid.service;

import java.util.List;

import org.sid.dao.CommentaireRepository;
import org.sid.entities.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {

	@Autowired
	private CommentaireRepository commentaireRepository;

	@Override
	public Commentaire saveComment(Commentaire commentaire) {

		return commentaireRepository.save(commentaire);
	}

	@Override
	public List<Commentaire> findCommentaireByTacheId(Long id) {
		 
		return commentaireRepository.findByTacheId(id);
	}

}
