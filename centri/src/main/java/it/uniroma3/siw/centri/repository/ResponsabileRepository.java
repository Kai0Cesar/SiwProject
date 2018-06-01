package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Responsabile;

public interface ResponsabileRepository extends CrudRepository<Responsabile, String> {

	public void delete(Responsabile responsabile);

	public List<Responsabile> findAll();

	public void deleteByEmail(String email);

	public Optional<Responsabile> findByEmail(String email);

	public Responsabile save(Responsabile responsabile);

}
