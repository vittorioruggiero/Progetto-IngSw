package com.example.ratatouille23server.Entity.Avviso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvvisoRepository extends JpaRepository<Avviso, Integer> {
}
