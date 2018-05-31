package it.uniroma3.siw.centri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Centro;
import it.uniroma3.siw.centri.repository.CentroRepository;

@Service
public class CentroService {
	@Autowired
	private CentroRepository centroRepository;

	public void delete(Centro centro) {
		centroRepository.delete(centro);
	}

	public void deleteById(String nome) {
		centroRepository.deleteById(nome);
	}

	public List<Centro> findAll() {
		return centroRepository.findAll();
	}

	public Optional<Centro> findById(String nome) {
		return centroRepository.findById(nome);
	}

	public Centro save(Centro centro) {
		return centroRepository.save(centro);
	}

}