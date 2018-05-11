package org.sid.service;

import java.util.List;

import org.sid.dao.TacheRepository;
import org.sid.entities.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacheServiceImpl implements TacheService {

	@Autowired
	private TacheRepository tacheRepository;

	@Override
	public Tache save(Tache tache) {

		return tacheRepository.save(tache);
	}

	@Override
	public List<Tache> findAll() {

		return tacheRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		tacheRepository.deleteById(id);

	}

	@Override
	public int getTotalTacheENcours() {

		return tacheRepository.getTotalTacheENcours();
	}

	@Override
	public int getTotalTacheNonCommence() {

		return tacheRepository.getTotalTacheNonCommence();
	}

	@Override
	public int getTotalTacheAnnule() {

		return tacheRepository.getTotalTacheAnnule();
	}

	@Override
	public int getTotalTacheTermine() {

		return tacheRepository.getTotalTacheTermine();
	}

}
