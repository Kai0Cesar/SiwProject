package it.uniroma3.siw.centri.services;

import java.util.List;

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

	public List<Attivita> findAll() {
		return (List<Attivita>) attivitaRepository.findAll();
	}

	public void deleteById(Long id) {
		attivitaRepository.deleteById(id);
	}

	public Attivita findById(Long id) {
		return attivitaRepository.findById(id).get();
	}

	public void save(Attivita attivita) {
		attivitaRepository.save(attivita);
	}

}
