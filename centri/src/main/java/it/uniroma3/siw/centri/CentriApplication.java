package it.uniroma3.siw.centri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.centri.service.ResponsabileService;

@SpringBootApplication
public class CentriApplication {

	@Autowired
	public ResponsabileService responsabileService;

	public static void main(String[] args) {
		SpringApplication.run(CentriApplication.class, args);
	}

}