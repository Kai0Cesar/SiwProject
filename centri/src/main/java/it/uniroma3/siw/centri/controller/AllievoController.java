package it.uniroma3.siw.centri.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.centri.model.Allievo;
import it.uniroma3.siw.centri.service.AllievoService;
import it.uniroma3.siw.centri.validator.AllievoValidator;

@Controller
public class AllievoController {
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private AllievoValidator validator;
	
	@RequestMapping("/allievi")
	public String allievi(Model model) {
		model.addAttribute("allievi",this.allievoService.findAll());
		return "lista-allievo";
	}

	@GetMapping("/")
	public String searchAllievo(@RequestParam("email") String email, Model model) {

		Optional<Allievo> allievo = this.allievoService.findByEmail(email);

		if (!allievo.isPresent()) {

			model.addAttribute("esistenza", "Allievo non presente");
			return getAllievi(model);
		}
		model.addAttribute("allievo", allievo.get());

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
	
	@RequestMapping(value = "/allievo/{id}", method = RequestMethod.GET)
	public String getAllievo(@PathVariable("id") String id, Model model) {
		model.addAttribute("allievo",this.allievoService.findByEmail(id));
		return "mostra-allievo";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
    public String newCustomer(@Valid @ModelAttribute("allievo") Allievo allievo,BindingResult bindingResult, 
    									Model model) {
        this.validator.validate(allievo, bindingResult);
        
        if (this.allievoService.existsByEmail(allievo.getEmail())) {
            model.addAttribute("exists", "Allievo già esistente");
            return "form-allievo";
        }
        else {
            if (!bindingResult.hasErrors()) {
                this.allievoService.save(allievo);
                model.addAttribute("allievi", this.allievoService.findAll());
                return "lista-allievo";
            }
        }
        return "form-allievo";
    }

}
