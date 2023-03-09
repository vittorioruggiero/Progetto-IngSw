package com.example.ratatouille23server.Entity.Conto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoRepository extends CrudRepository<Conto, Integer> {
}
