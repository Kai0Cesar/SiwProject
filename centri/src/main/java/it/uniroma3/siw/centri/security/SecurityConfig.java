package it.uniroma3.siw.centri.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/*private final String usersQuery = "SELECT email,password FROM responsabile WHERE email = ?";
	private final String rolesQuery = "SELECT email,ruolo FROM responsabile WHERE email = ?";
	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;
	

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**", "/css/**", "/images/**", "/js/**", "/vendor/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder())
				.usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/", "/index", "/login").permitAll() // possono accedere tutti a queste risorse
			.antMatchers("/admin/**").hasRole("ADMIN") // /admin/** accessibile solo a utenti con ruolo ADMIN
			.antMatchers("/user/**").hasRole("USER") // /user/** accessibile solo a utenti con ruolo USER
			.anyRequest().permitAll().anyRequest()
			.authenticated().and().formLogin().loginPage("/login") // pagina di login
			.defaultSuccessUrl("/role").and().logout().logoutSuccessUrl("/login").permitAll().and()
			.exceptionHandling();
	}*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		 auth.jdbcAuthentication().dataSource(dataSource)
//		 .passwordEncoder(new BCryptPasswordEncoder())
//		 .usersByUsernameQuery("SELECT username,password,1 FROM users where username=?")
//		 .authoritiesByUsernameQuery("SELECT username,authority FROM authorities where username=?");
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("progetto").password("progetto").roles("prova"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.logout().permitAll();
	}
}