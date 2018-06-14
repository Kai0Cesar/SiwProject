package it.uniroma3.siw.centri.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, String> {

	public void deleteByEmail(String email);

	public List<Allievo> findAll();

}
