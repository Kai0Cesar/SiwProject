package it.uniroma3.siw.centri.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Attivita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date oraInizio;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date oraFine;

	@ManyToOne
	private Centro centro;

	@ManyToMany
	private List<Allievo> allievi;

	public Attivita() {
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(Date oraInizio) {
		this.oraInizio = oraInizio;
	}

	public Date getOraFine() {
		return oraFine;
	}

	public void setOraFine(Date oraFine) {
		this.oraFine = oraFine;
	}

	public List<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}

	public void addAllievo(Allievo allievo) {
		this.allievi.add(allievo);
	}

	public void removeAllievo(Allievo allievo) {
		this.allievi.remove(allievo);
	}
	
	public Allievo getRiserva() {
		Allievo riserva;
		try {
		riserva=this.allievi.get(this.centro.getCapienzaMassima());
		}catch (IndexOutOfBoundsException e) {
			riserva=null;
		}
		return riserva;
	}
	
	public boolean privilegiato(Allievo allievo) {
		
		boolean privilegiato=false;
		int i=0;
		while( i<this.centro.getCapienzaMassima() && !privilegiato) {
			privilegiato=this.allievi.get(i).equals(allievo);
			i++;
		}
		
		return privilegiato;
	}

}
