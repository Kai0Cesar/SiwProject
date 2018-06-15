package it.uniroma3.siw.centri.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.centri.model.Centro;
import it.uniroma3.siw.centri.service.CentroService;

@Controller
public class CentroController {

	@Autowired
	private CentroService centroService;

	@GetMapping("/centri")
	public String getAllievi(Model model) {

		model.addAttribute("centri", this.centroService.findAll());

		return "lista-centri";
	}

	@GetMapping("/nuovoCentro")
	public String showFormCentro(Centro centro) {
		return "form-centro";
	}

	@PostMapping("/nuovoCentro")
	public String nuovoCentro(@Valid @ModelAttribute Centro centro, Model model) {

		if (this.centroService.existsByIndirizzo(centro.getIndirizzo())) {
			model.addAttribute("esistenza", "Centro già esistente");
			return "form-centro";
		}
		this.centroService.save(centro);
		model.addAttribute("centri", this.centroService.findAll());
		return "lista-centri";
	}

}
