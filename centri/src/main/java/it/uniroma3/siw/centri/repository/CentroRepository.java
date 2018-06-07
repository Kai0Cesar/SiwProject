package it.uniroma3.siw.centri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Centro;

public interface CentroRepository extends CrudRepository<Centro, String> {

	public void delete(Centro centro);

	public void deleteById(String nome);

	public List<Centro> findAll();

	public Optional<Centro> findById(String nome);

	public Centro save(Centro centro);

}
