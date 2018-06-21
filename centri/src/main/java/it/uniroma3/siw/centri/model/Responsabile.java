package it.uniroma3.siw.centri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Responsabile {

	@Id
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String ruolo;

	private String telefono;

	@ManyToOne
	private Centro centro;

	public Responsabile() {
	}

	public Responsabile(String email, String nome, String cognome, String password, String ruolo) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Long getCentroId() {
		return this.centro.getId();
	}

	@Override
	public String toString() {
		return "Responsabile [centro=" + centro + "]" + super.toString();
	}
}
