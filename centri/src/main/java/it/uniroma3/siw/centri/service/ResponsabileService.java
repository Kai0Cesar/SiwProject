package it.uniroma3.siw.centri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.centri.model.Responsabile;
import it.uniroma3.siw.centri.repository.ResponsabileRepository;

@Service
@Transactional
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

	public  Responsabile findByEmail(String email){
		Optional<Responsabile> responsabile= responsabileRepository.findById(email);
		if (!responsabile.isPresent())
			return null;

		return responsabile.get();
	}

	public Responsabile save(Responsabile responsabile) {
		return responsabileRepository.save(responsabile);
	}
	
	public Responsabile getCorrente() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		return this.findByEmail(auth.getName());
	}

	
	
}
