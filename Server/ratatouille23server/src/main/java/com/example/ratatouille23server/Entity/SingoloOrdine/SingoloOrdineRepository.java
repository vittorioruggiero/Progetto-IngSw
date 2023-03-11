package com.example.ratatouille23server.Entity.SingoloOrdine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingoloOrdineRepository extends JpaRepository<SingoloOrdine, Integer> {
}
