package it.uniroma3.siw.centri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.repository.AllievoRepository;
import it.uniroma3.siw.centri.repository.AttivitaRepository;

@Service
@Transactional
public class AllievoService {

	@Autowired
	private AllievoRepository allievoRepository;
	
	@Autowired
	private AttivitaRepository attivitaRepository;

	public long count() {
		return allievoRepository.count();
	}

	public void delete(Allievo allievo) {
		allievoRepository.delete(allievo);
	}

	public void deleteByEmail(String email) {
		allievoRepository.deleteById(email);
	}

	public boolean existsByEmail(String email) {
		return allievoRepository.existsById(email);
	}

	public List<Allievo> findAll() {
		return allievoRepository.findAll();
	}

	public Optional<Allievo> findByEmail(String email) {
		return allievoRepository.findById(email);
	}

	public Allievo save(Allievo allievo) {
		return allievoRepository.save(allievo);
	}
	
	public List<Allievo> findAllByAttivitaId(Long id){
		return allievoRepository.findAllByAttivitaId(id);
	}
	
	public Allievo findAllByAttivitaIdAndEmail(Long id,String email) {
		return allievoRepository.findAllByAttivitaIdAndEmail(id, email);
	}
	
	public void addByAttivitaId(Long id,String email) {
		Attivita a = this.attivitaRepository.findById(id).get();
		Allievo alievo=this.findByEmail(email).get();
		a.getAllievi().add(alievo);
	}

}
