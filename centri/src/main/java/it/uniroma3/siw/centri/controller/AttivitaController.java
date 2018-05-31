package it.uniroma3.siw.centri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.centri.model.Attivita;
<<<<<<< HEAD
import it.uniroma3.siw.centri.services.AttivitaService;
=======
import it.uniroma3.siw.centri.service.AttivitaService;
>>>>>>> branch-1.1

@Controller
@RequestMapping("/attivita")
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
<<<<<<< HEAD
	@GetMapping("/list")
=======
	@GetMapping("/lista")
>>>>>>> branch-1.1
	public String listCustomer(Model model) {

		List<Attivita> attivita = attivitaService.findAll();

		model.addAttribute("attivita", attivita);

<<<<<<< HEAD
		return "list-attivita";
=======
		return "lista-attivita";
>>>>>>> branch-1.1
	}
	
	@GetMapping("/showFormAdd")
	public String showFormAdd(Model model) {

		Attivita attivita = new Attivita();

		model.addAttribute("attivita", attivita);

		return "attivita-form";
	}
	
<<<<<<< HEAD
	@PostMapping("/salvaAttivita")
=======
	@PostMapping("/saveAttivita")
>>>>>>> branch-1.1
	public String saveCustomer(@ModelAttribute("attivita") Attivita attivita) {
		
		attivitaService.save(attivita);

<<<<<<< HEAD
		return "redirect:/attivita/list";
=======
		return "redirect:/attivita/lista";
>>>>>>> branch-1.1
	}


}
