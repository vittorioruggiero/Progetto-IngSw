package com.example.ratatouille23server.Entity.AddettoSala;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddettoSalaRepository extends JpaRepository<AddettoSala, String> {

    AddettoSala findByNomeUtente(String nomeUtente);
}
