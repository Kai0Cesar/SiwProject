package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, String> {

	public long count();

	public void delete(Allievo allievo);

<<<<<<< HEAD
	public void deleteById(String email);

	public boolean existsById(String email);

	public List<Allievo> findAll();

	public Optional<Allievo> findById(String email);
=======
	public void deleteByEmail(String email);

	public boolean existsByEmail(String email);

	public boolean alreadyExists(Allievo allievo);
	
	public List<Allievo> findAll();

	public Optional<Allievo> findByEmail(String email);
>>>>>>> branch-1.1

	public Allievo save(Allievo allievo);

}
