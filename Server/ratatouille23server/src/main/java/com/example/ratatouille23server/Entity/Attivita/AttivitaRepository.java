package com.example.ratatouille23server.Entity.Attivita;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepository extends CrudRepository<Attivita, AttivitaPkey> {

}
