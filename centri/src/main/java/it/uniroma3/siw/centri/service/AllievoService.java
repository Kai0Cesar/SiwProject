package it.uniroma3.siw.centri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.repository.AllievoRepository;

@Service
@Transactional
public class AllievoService {

	@Autowired
	private AllievoRepository allievoRepository;
	

	public long count() {
		return allievoRepository.count();
	}
	
	public void delete(Allievo allievo) {
		allievoRepository.delete(allievo);
	}

	public void deleteByEmail(String email) {
		allievoRepository.deleteByEmail(email);
	}

	public boolean existsByEmail(String email) {
		return allievoRepository.existsById(email);
	}
	
	/*public boolean alreadyExists(Allievo allievo) {
		return allievoRepository.alreadyExists(allievo);
	}*/

	public List<Allievo> findAll(){
		return allievoRepository.findAll();
	}

	public Optional<Allievo> findByEmail(String email) {
		return  allievoRepository.findById(email);
	}

	public Allievo save(Allievo allievo) {
		return allievoRepository.save(allievo);
	}
	
	
}
