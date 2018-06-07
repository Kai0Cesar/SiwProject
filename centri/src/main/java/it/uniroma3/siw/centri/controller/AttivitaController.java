package it.uniroma3.siw.centri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.service.AttivitaService;

@Controller
@RequestMapping("/attivita")
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	@GetMapping("/lista")
	private String attivita(Model model) {
		
		List<Attivita> attivita = this.attivitaService.findAll();
		model.addAttribute(attivita);
		
		return "lista-attivita";
	}


}
