package it.uniroma3.siw.centri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Responsabile;
import it.uniroma3.siw.centri.repository.ResponsabileRepository;

@Service
public class ResponsabileService {

	@Autowired
	private ResponsabileRepository responsabileRepository;
	
	public void delete(Responsabile responsabile) {
		responsabileRepository.delete(responsabile);
	}

	public void deleteByEmail(String email) {
		responsabileRepository.deleteByEmail(email);
	}
	public List<Responsabile> findAll(){
		return responsabileRepository.findAll();
	}

	public  Optional<Responsabile> findByEmail(String email){
		return responsabileRepository.findByEmail(email);
	}

	public Responsabile save(Responsabile responsabile) {
		return responsabileRepository.save(responsabile);
	}

	
	
}
