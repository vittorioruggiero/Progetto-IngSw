package com.example.ratatouille23server.Entity.Amministratore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends JpaRepository<Amministratore, String> {

    Amministratore findByNomeUtente(String nomeUtente);

}
