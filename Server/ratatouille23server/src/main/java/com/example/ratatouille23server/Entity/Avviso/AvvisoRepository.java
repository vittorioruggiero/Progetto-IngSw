package com.example.ratatouille23server.Entity.Avviso;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvvisoRepository extends CrudRepository<Avviso, Integer> {
}
