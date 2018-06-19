package it.uniroma3.siw.centri.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.repository.AttivitaRepository;

@Service
@Transactional
public class AttivitaService {

	@Autowired
	private AttivitaRepository attivitaRepository;

	public void delete(Attivita attivita) {
		attivitaRepository.delete(attivita);
	}

	public void deleteById(Long id) {
		attivitaRepository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return attivitaRepository.existsById(id);
	}

	public boolean existsByOra(Date oraInizio) {
		return attivitaRepository.existsByOraInizio(oraInizio);
	}

	public boolean existsByData(Date data) {
		return attivitaRepository.existsByData(data);
	}

	public List<Attivita> findAll() {
		return attivitaRepository.findAll();
	}

	public List<Attivita> findAllByOrderByDataAscOraAsc() {
		return attivitaRepository.findAllByOrderByDataAscOraInizioAsc();
	}

	public Attivita findById(Long id) {
		Optional<Attivita> attivita = attivitaRepository.findById(id);
		if (!attivita.isPresent())
			return null;

		return attivita.get();
	}

	public Attivita save(Attivita attivita) {
		return attivitaRepository.save(attivita);
	}

	public List<Attivita> findAllByCentroId(Long id) {
		return attivitaRepository.findAllByCentroId(id);
	}

}
