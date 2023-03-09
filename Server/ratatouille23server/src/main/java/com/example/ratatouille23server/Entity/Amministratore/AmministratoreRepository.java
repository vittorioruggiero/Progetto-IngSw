package com.example.ratatouille23server.Entity.Amministratore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends CrudRepository<Amministratore, String> {
}
