package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, String> {

	public long count();

	public void delete(Allievo allievo);

	public void deleteById(String email);

	public boolean existsById(Allievo allievo);

	public List<Allievo> findAll();

	public Optional<Allievo> findById(String email);

	public Allievo save(Allievo allievo);

}
