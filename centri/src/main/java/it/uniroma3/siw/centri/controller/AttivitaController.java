package it.uniroma3.siw.centri.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.service.AttivitaService;

@Controller
public class AttivitaController {

	@Autowired
	private AttivitaService attivitaService;

	@GetMapping("/attivita/lista")
	private String attivita(Model model) {

		List<Attivita> attivita = this.attivitaService.findAllByOrderByDataAscOraAsc();
		model.addAttribute("attivita", attivita);

		return "lista-attivita";
	}

	@GetMapping("/centro/{id}/attivita")
	private String attivitaCentro(@PathVariable("id") Long id, Model model) {

		List<Attivita> attivita = this.attivitaService.findAllByCentroId(id);
		model.addAttribute("attivita", attivita);

		return "lista-attivita";
	}

	@PostMapping("/attivita/nuovaAttivita")
	public String newAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, BindingResult bindingResult,
			Model model) {

		// this.attivitaValidator.validate(attivita, bindingResult);

		if (this.attivitaService.existsByOra(attivita.getOraInizio())
				&& this.attivitaService.existsByData(attivita.getData())) {
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

	@GetMapping("/attivita/nuovaAttivita")
	public String showFormAttivita(Attivita attivita) {
		return "form-attivita";
	}

}
