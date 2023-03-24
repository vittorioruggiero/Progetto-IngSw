package com.example.ratatouille23server.Entity.Conto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Integer> {

    List<Conto> findByDataBetween(java.sql.Date dataInizio, java.sql.Date dataFine);
}
