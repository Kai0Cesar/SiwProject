package it.uniroma3.siw.centri.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.service.AttivitaService;

@Controller
@RequestMapping("/attivita")
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	
//	@GetMapping("/lista")
//	public String getAllievi(Model model) {
//
//		List<Allievo> allievi = this.attivitaService.findAllAllievi();
//
//		model.addAttribute("allievi", allievi);
//
//		return "lista-allievi";
//	}
	
	
	@GetMapping("/lista")
	private String attivita(Model model) {
		
		List<Attivita> attivita = this.attivitaService.findAllByOrderByDataAscOraAsc();
		model.addAttribute("attivita",attivita);
		
		return "lista-attivita";
	}
	
	
	@PostMapping("/nuovaAttivita")
	public String newAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, BindingResult bindingResult, Model model) {

//		this.attivitaValidator.validate(attivita, bindingResult);

		if (this.attivitaService.existsByOra(attivita.getOraInizio()) && this.attivitaService.existsByData(attivita.getData()) ) {
			model.addAttribute("esistenza", "Orario non disponibile");
			return "form-attivita";
		} else {
			if (!bindingResult.hasErrors()) {
				this.attivitaService.save(attivita);
				return "redirect:/attivita/lista";
			}
		}
		return "form-attivita";

	}
	
	@GetMapping("/nuovaAttivita")
	public String showFormAttivita(Attivita attivita) {
		return "form-attivita";
	}


}
