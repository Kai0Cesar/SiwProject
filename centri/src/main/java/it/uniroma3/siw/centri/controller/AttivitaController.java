package it.uniroma3.siw.centri.controller;

import java.util.List;

import javax.mail.MessagingException;
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
import it.uniroma3.siw.centri.model.Attivita;
import it.uniroma3.siw.centri.model.EmailSender;
import it.uniroma3.siw.centri.service.AttivitaService;
import it.uniroma3.siw.centri.service.CentroService;

@Controller
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private EmailSender emailSender;

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
	public String showFormAttivita(Model model) {
		model.addAttribute("attivita", new Attivita());
		model.addAttribute("centri",centroService.findAll());
		return "form-attivita";
	}
	
	@GetMapping("/attivita/")
	public String searchAttivita(@RequestParam("id") Long id, Model model) {

		Attivita attivita = this.attivitaService.findById(id);

		if (attivita==null) {

			model.addAttribute("esistenza", "Attivita non trovata");
			return attivita(model);
		}
		model.addAttribute("attivita", attivita);

		return "mostra-allievo";
	}
	
	@GetMapping("/attivita/{id}/elimina")
	private String eliminaAttivita(@PathVariable("id") Long id, Model model) throws MessagingException {

		Attivita attivita=this.attivitaService.findById(id);
		if(attivita!=null) {
			
			for(Allievo allievo :attivita.getAllievi()) {
				this.emailSender.send(allievo.getEmail(),"Attività annullata","Caro allievo, ci dispiace informarla che l'attività "+attivita.getNome()+" è stata annullata");
			}
			attivita.getAllievi().clear();  					
			attivita.getCentro().getAttivita().remove(attivita);
			this.attivitaService.deleteById(id);
			model.addAttribute("attivita", this.attivitaService.findAllByCentroId(attivita.getCentro().getId()));
			return "lista-attivita";
		}
		return "home-page";
	}
	
	
}
