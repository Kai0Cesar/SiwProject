package it.uniroma3.siw.centri.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Centro;

public interface CentroRepository extends CrudRepository<Centro, Long> {

	public void deleteById(Long id);

	public List<Centro> findAll();
	
	public boolean existsByIndirizzo(String indirizzo);
}
