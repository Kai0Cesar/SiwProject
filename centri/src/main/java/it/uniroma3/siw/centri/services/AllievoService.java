package it.uniroma3.siw.centri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.repository.AllievoRepository;

@Service
public class AllievoService {

	@Autowired
	private AllievoRepository allievoRepository;
	

	public long count() {
		return allievoRepository.count();
	}
	
	public void delete(Allievo allievo) {
		allievoRepository.delete(allievo);
	}

	public void deleteById(String email) {
		allievoRepository.deleteById(email);
	}

	public boolean existsById(String email) {
		return allievoRepository.existsById(email);
	}

	public List<Allievo> findAll(){
		return allievoRepository.findAll();
	}

	public Optional<Allievo> findById(String email) {
		return  allievoRepository.findById(email);
	}

	public Allievo save(Allievo a) {
		return allievoRepository.save(a);
	}
	
	
}
