package it.uniroma3.siw.centri.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.centri.model.Responsabile;

@Service
public class ResponsabileUserDetailsService implements UserDetailsService {

  @Autowired
  private ResponsabileService responsabileService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Responsabile> responsabile= responsabileService.findByEmail(email);

    UserBuilder builder = null;
    if (responsabile.isPresent()) {
      builder = org.springframework.security.core.userdetails.User.withUsername(email);
      builder.password(new BCryptPasswordEncoder().encode(responsabile.get().getPassword()));
      builder.roles(responsabile.get().getRuolo());
    } else {
      throw new UsernameNotFoundException("User not found.");
    }

    return builder.build();
  }
}