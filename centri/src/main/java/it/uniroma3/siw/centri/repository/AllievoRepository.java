package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, String> {

	public long count();

	public void delete(Allievo allievo);

	public void deleteByEmail(String email);

	public boolean existsByEmail(String email);

	//public boolean alreadyExists(Allievo allievo);
	
	public List<Allievo> findAll();

	public Optional<Allievo> findByEmail(String email);

	public Allievo save(Allievo allievo);

}
