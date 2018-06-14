package it.uniroma3.siw.centri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.centri.model.Centro;
import it.uniroma3.siw.centri.repository.CentroRepository;

@Service
@Transactional
public class CentroService {
	@Autowired
	private CentroRepository centroRepository;

	public void delete(Centro centro) {
		centroRepository.delete(centro);
	}

	public void deleteById(Long id) {
		centroRepository.deleteById(id);
	}

	public List<Centro> findAll() {
		return centroRepository.findAll();
	}

	public Optional<Centro> findById(Long id) {
		return centroRepository.findById(id);
	}

	public Centro save(Centro centro) {
		return centroRepository.save(centro);
	}

}
