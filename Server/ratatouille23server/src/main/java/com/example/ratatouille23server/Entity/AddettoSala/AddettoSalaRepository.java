package com.example.ratatouille23server.Entity.AddettoSala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddettoSalaRepository extends JpaRepository<AddettoSala, String> {
}
