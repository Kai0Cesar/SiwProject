package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Attivita;

public interface AttivitaRepository extends CrudRepository<Attivita, Long> {

	public void delete(Attivita attivita);

	public void deleteById(Long id);

	public boolean existsById(Attivita attivita);

	public List<Attivita> findAll();

	public Optional<Attivita> findById(Long id);

	public Attivita save(Attivita attivita);

}
