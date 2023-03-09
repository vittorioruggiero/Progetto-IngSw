package com.example.ratatouille23server.Entity.Ordinazione;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdinazioneRepository extends CrudRepository<Ordinazione, Integer> {
}
