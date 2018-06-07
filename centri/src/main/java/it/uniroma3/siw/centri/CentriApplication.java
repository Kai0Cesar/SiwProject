package it.uniroma3.siw.centri;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.centri.model.Responsabile;
import it.uniroma3.siw.centri.service.ResponsabileService;

@SpringBootApplication
public class CentriApplication {

	@Autowired
	public ResponsabileService responsabileService;

	public static void main(String[] args) {
		SpringApplication.run(CentriApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Responsabile responsabile = new Responsabile("email", "user", "cognome", "password","ruolo");
		responsabileService.save(responsabile);
	}

}