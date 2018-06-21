package it.uniroma3.siw.centri.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.centri.model.Responsabile;
import it.uniroma3.siw.centri.service.ResponsabileService;

@Controller
public class LoginLogoutController {

	private static String authorizationRequestBaseUri = "oauth2/authorization";

	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private ResponsabileService responsabileService;

	@SuppressWarnings("unchecked")
	@GetMapping("/login")
	public String getLoginOAuth(Model model) {

		Iterable<ClientRegistration> clientRegistrations = null;
		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
		if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
			clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
		}
		clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
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

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping(value = {"/loginSuccessful"})
	protected String showHomePage(HttpSession session, HttpServletRequest request, Model model) {
		if ((responsabileService.getCorrente()) == null) {
			Responsabile responsabile = null;
			try {
				// Form login
				UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				responsabile = responsabileService.findByEmail(user.getUsername());
			} catch (Exception e) {
				// oAuth login
				DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				responsabile = responsabileService.findByEmail(user.getEmail());

				// Se non è registrato nel database
				if (responsabile == null)
					try {
						request.logout();
						getLoginOAuth(model);
						model.addAttribute("urls", oauth2AuthenticationUrls);
						return "login";
					} catch (ServletException e1) {

						e1.printStackTrace();
					}
			}
			session.setAttribute("email", responsabile.getEmail());
			session.setAttribute("responsabile", responsabile);
		}

		return "home-page";
	}

}
