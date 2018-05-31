package it.uniroma3.siw.centri.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Responsabile;

@Service
public class ResponsabileService {

	@Autowired
	private ResponsabileService responsabileService;
	
	public void delete(Responsabile responsabile) {
		responsabileService.delete(responsabile);
	}

	public void deleteById(String email) {
		responsabileService.deleteById(email);
	}
	public List<Responsabile> findAll(){
		return responsabileService.findAll();
	}

	public  Optional<Responsabile> findById(String email){
		return responsabileService.findById(email);
	}

	public Responsabile save(Responsabile responsabile) {
		return responsabileService.save(responsabile);
	}

	
	
}
