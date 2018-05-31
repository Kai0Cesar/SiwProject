package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Responsabile;

public interface ResponsabileRepository extends CrudRepository<Responsabile, String> {

	public void delete(Responsabile responsabile);

<<<<<<< HEAD
	public void deleteById(String email);
	
	public List<Responsabile> findAll();

	public Optional<Responsabile> findById(String email);
=======
	public void deleteByEmail(String email);
	
	public List<Responsabile> findAll();

	public Optional<Responsabile> findByEmail(String email);
>>>>>>> branch-1.1

	public Responsabile  save(Responsabile responsabile);

	
}
