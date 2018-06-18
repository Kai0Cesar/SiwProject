package it.uniroma3.siw.centri.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Attivita;

public interface AttivitaRepository extends CrudRepository<Attivita, Long> {

	public boolean existsByOraInizio(Date oraInizio);

	public boolean existsByData(Date data);

	public List<Attivita> findAllByOrderByDataAscOraInizioAsc();

	public List<Attivita> findAll();

	public List<Attivita> findAllByCentroId(Long id);

}
