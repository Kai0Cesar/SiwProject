package it.uniroma3.siw.centri.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.service.AllievoService;
import it.uniroma3.siw.centri.validator.AllievoValidator;

@Controller
public class AllievoController {

	@Autowired
	public AllievoService allievoService;

	@Autowired
	public AllievoValidator allievoValidator;

	@GetMapping("/allievo/lista")
	public String getAllievi(Model model) {

		List<Allievo> allievi = this.allievoService.findAll();

		model.addAttribute("allievi", allievi);
		return "lista-allievi";
	}
	
	@GetMapping("/attivita/{id}/allievi")
	private String allieviAttivita(@PathVariable("id") Long id,Model model) {
		
		List<Allievo> allievi = this.allievoService.findAllByAttivitaId(id);
		model.addAttribute("allievi",allievi);
		
		return "lista-allievi";
	}

	@GetMapping("/allievo/")
	public String searchAllievo(@RequestParam("email") String email, Model model) {

		Optional<Allievo> allievo = this.allievoService.findByEmail(email);

		if (!allievo.isPresent()) {

			model.addAttribute("esistenza", "Allievo non trovato");
			return getAllievi(model);
		}
		model.addAttribute("allievo", allievo.get());

		return "mostra-allievo";
	}

	@PostMapping("/allievo/nuovoAllievo")
	public String newAllievo(@Valid @ModelAttribute("allievo") Allievo allievo, BindingResult bindingResult, Model model) {

		this.allievoValidator.validate(allievo, bindingResult);

		if (this.allievoService.existsByEmail(allievo.getEmail())) {
			model.addAttribute("esistenza", "Allievo già iscritto");
			return "form-allievo";
		} else {
			if (!bindingResult.hasErrors()) {
				this.allievoService.save(allievo);
				return "redirect:/allievo/lista";
			}
		}
		return "form-allievo";

	}

	@GetMapping("/allievo/nuovoAllievo")
	public String showFormAllievo(Allievo allievo) {
		return "form-allievo";

	}

}
