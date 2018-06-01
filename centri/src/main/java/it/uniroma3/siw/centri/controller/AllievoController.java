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
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.service.AllievoService;
import it.uniroma3.siw.centri.validator.AllievoValidator;

@Controller
@RequestMapping("/allievo")
public class AllievoController {

	@Autowired
	public AllievoService allievoService;

	@Autowired
	public AllievoValidator allievoValidator;

	@GetMapping("/lista")
	public String getAllievi(Model model) {

		List<Allievo> allievi = this.allievoService.findAll();

		model.addAttribute("allievi", allievi);

		return "lista-allievi";
	}

	@GetMapping("/{email}")
	public String getAllievo(@PathVariable("email") String email, Model model) {

		model.addAttribute("allievo", this.allievoService.findByEmail(email));

		return "mostra-allievo";
	}

	@PostMapping("/nuovoAllievo")
	public String newAllievo(@Valid @ModelAttribute("allievo") Allievo allievo, Model model,
			BindingResult bindingResult) {

		this.allievoValidator.validate(allievo, bindingResult);

		if (this.allievoService.existsByEmail(allievo.getEmail())) {
			model.addAttribute("esistenza", "Allievo già presente");
			return "form-allievo";
		} else {
			if (!bindingResult.hasErrors()) {
				this.allievoService.save(allievo);
				model.addAttribute("allievi", allievoService.findAll());
				return "redirect:/allievo/lista";
			}
		}
		return "form-allievo";

	}

	@GetMapping("/nuovoAllievo")
	public String newFormAllievo(Allievo allievo) {
		return "form-allievo";

	}

}
