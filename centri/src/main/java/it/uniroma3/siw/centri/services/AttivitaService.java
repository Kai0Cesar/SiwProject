package it.uniroma3.siw.centri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.repository.AttivitaRepository;

@Service
public class AttivitaService {

	@Autowired
	private AttivitaRepository attivitaRepository;

	public void delete(Attivita attivita) {
		attivitaRepository.delete(attivita);
	}

	public void deleteById(Long id) {
		attivitaRepository.deleteById(id);
	}

	public List<Attivita> findAll() {
		return attivitaRepository.findAll();
	}

	public Optional<Attivita> findById(Long id) {
		return attivitaRepository.findById(id);
	}

	public Attivita save(Attivita attivita) {
		return attivitaRepository.save(attivita);
	}

}
