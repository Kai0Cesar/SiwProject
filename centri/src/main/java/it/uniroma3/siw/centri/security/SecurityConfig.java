package it.uniroma3.siw.centri.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("progetto").password("progetto").roles("prova"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		 .authorizeRequests()
		 	.anyRequest().authenticated()
		 	.and()
		 .formLogin()
		 	.loginPage("/login")
		 	.permitAll()
		 	.and()
		.logout()
			.permitAll();
	}
}