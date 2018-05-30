package it.uniroma3.siw.centri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	
	@GetMapping("/home")
	public String home() {
		return "home-page";
	}
	
}
