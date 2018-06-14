package it.uniroma3.siw.centri.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Centro {
	
	@Id
	private Long id;
	
	private String nome;
	
	@Column(nullable = false)
	private String indirizzo;

	private String email;
	
	private Integer capienzaMassima;

	@Column(nullable = false)
	private String telefono;
	
	@OneToMany(mappedBy="centro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Attivita> attivita;
	
	@OneToMany(mappedBy="centro", cascade = CascadeType.ALL)
	private List<Responsabile> responsabili;

	public Centro() {
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getCapienzaMassima() {
		return capienzaMassima;
	}

	public void setCapienzaMassima(Integer capienzaMassima) {
		this.capienzaMassima = capienzaMassima;
	}

	public List< Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(List< Attivita> attivita) {
		this.attivita = attivita;
	}
	
	public void addAttivita(Attivita attivita) {
		this.attivita.add(attivita.getId().intValue(), attivita);
	}
}
