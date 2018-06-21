package it.uniroma3.siw.centri.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginLogoutController {
	
	private static String authorizationRequestBaseUri = "oauth2/authorization";
	
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
	
	@Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/login")
	public String getLoginOAuth(Model model) {
		Iterable<ClientRegistration> clientRegistrations = null;
	    ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
	    if (type != ResolvableType.NONE && 
	    ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
	    clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
	    }	 
	    clientRegistrations.forEach(registration -> 
	      oauth2AuthenticationUrls.put(registration.getClientName(), 
	      authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
	    model.addAttribute("urls", oauth2AuthenticationUrls);
		
		return "login";
	}
	
	@GetMapping("/errore")
	public String getErrore() {
		return "errore";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth); 
		}
		return "redirect:/login";		
	}
}
