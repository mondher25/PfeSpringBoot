package org.sid.service;

import java.util.List;

import org.sid.entities.Commentaire;


public interface CommentaireService {
	Commentaire saveComment(Commentaire commentaire);
	List<Commentaire> findCommentaireByTacheId(Long id);
}
