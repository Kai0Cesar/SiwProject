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
import it.uniroma3.siw.centri.service.AllievoService;
import it.uniroma3.siw.centri.service.AttivitaService;
import it.uniroma3.siw.centri.validator.AllievoValidator;

@Controller
public class AllievoController {

	@Autowired
	public AllievoService allievoService;

	@Autowired
	public AttivitaService attivitaService;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	public AllievoValidator allievoValidator;

	@GetMapping("/allievo/lista")
	public String getAllievi(Model model) {

		List<Allievo> allievi = this.allievoService.findAll();

		model.addAttribute("allievi", allievi);
		return "lista-allievi";
	}

	@GetMapping("/attivita/{id}/allievi")
	private String allieviAttivita(@PathVariable("id") Long id, Model model) {

		List<Allievo> allievi = this.allievoService.findAllByAttivitaId(id);
		model.addAttribute("allievi", allievi);

		return "attivita-allievi";
	}

	@PostMapping("/attivita/{id}/iscriviAllievo")
	private String inserisciAllievoAttivita(@PathVariable("id") Long id, @RequestParam("email") String email,
			Model model) {

		Allievo allievo = this.allievoService.findAllByAttivitaIdAndEmail(id, email);
		Allievo allievoDB = this.allievoService.findByEmail(email);

		if (allievo != null) {
			model.addAttribute("esistenzaAttivita", "Allievo già iscritto all'attività");
		} else {
			if (allievoDB == null)
				model.addAttribute("esistenzaDB", "Allievo non registrato");
			else {
				model.addAttribute("esistenza", "Allievo iscritto all'attività con successo");
				this.allievoService.addByAttivitaId(id, email);
			}

		}
		List<Allievo> allievi = this.allievoService.findAllByAttivitaId(id);
		model.addAttribute("allievi", allievi);

		return "attivita-allievi";
	}

	@PostMapping("/attivita/{id}/disiscriviAllievo")
	private String disiscriviAllievoAttivita(@PathVariable("id") Long id, @RequestParam("email") String email,
			Model model) throws MessagingException {
		Allievo allievo = this.allievoService.findAllByAttivitaIdAndEmail(id, email);

		if (allievo == null)
			model.addAttribute("presenzaAttivita", "Allievo non è iscritto all'attività");
		else {
			Attivita attivita = this.attivitaService.findById(id);
			Allievo riserva = attivita.getRiserva();

			if (riserva != null && attivita.privilegiato(allievo)) {
				this.emailSender.send(riserva.getEmail(), "Privilegiato",
						"Caro allievo, le informiamo che, a causa di una disiscrizione da parte di un altro allievo, lei ora non è più una riserva all'attività "
								+ attivita.getNome() + ".Buona giornata.");

			}
			attivita.removeAllievo(allievo);
			allievo.removeAttivita(attivita);
			
		}

		List<Allievo> allievi = this.allievoService.findAllByAttivitaId(id);
		model.addAttribute("allievi", allievi);

		return "attivita-allievi";
	}

	@GetMapping("/allievo/")
	public String searchAllievo(@RequestParam("email") String email, Model model) {

		Allievo allievo = this.allievoService.findByEmail(email);

		if (allievo == null) {

			model.addAttribute("esistenza", "Allievo non trovato");
			return getAllievi(model);
		}
		model.addAttribute("allievo", allievo);

		return "mostra-allievo";
	}

	@PostMapping("/allievo/nuovoAllievo")
	public String newAllievo(@Valid @ModelAttribute("allievo") Allievo allievo, BindingResult bindingResult,
			Model model) {

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
