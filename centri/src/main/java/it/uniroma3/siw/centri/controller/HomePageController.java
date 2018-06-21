package it.uniroma3.siw.centri.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	
	
	@GetMapping("/")
	public String home(Authentication auth) {
		return "home-page";
	}
	
	@GetMapping("/casiDuso")
	public String casiDuso() {
		return "casiDuso";
	}
	
}
