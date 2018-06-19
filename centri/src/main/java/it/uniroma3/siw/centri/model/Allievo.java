package it.uniroma3.siw.centri.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Allievo {

	@Id
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	private String telefono;

	private String luogoDiNascita;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDiNascita;
	
	@ManyToMany(mappedBy="allievi")
	private List<Attivita> attivita;

	public Allievo() {
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<Attivita> attivita) {
		this.attivita = attivita;
	}
	
	public void removeAttivita(Attivita attivita) {
		this.attivita.remove(attivita);
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.luogoDiNascita + " " + this.dataDiNascita.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		Allievo that =(Allievo) o;
		return this.getEmail().equals(that.getEmail());
	}

}
