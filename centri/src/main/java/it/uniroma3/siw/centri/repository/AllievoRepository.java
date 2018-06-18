package it.uniroma3.siw.centri.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.centri.model.Allievo;

public interface AllievoRepository extends CrudRepository<Allievo, String> {

	public List<Allievo> findAll();

	public List<Allievo> findAllByAttivitaId(Long id);

	public Allievo findAllByAttivitaIdAndEmail(Long id, String email);

}
