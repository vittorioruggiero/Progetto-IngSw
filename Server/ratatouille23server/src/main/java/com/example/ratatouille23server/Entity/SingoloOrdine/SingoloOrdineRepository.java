package com.example.ratatouille23server.Entity.SingoloOrdine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingoloOrdineRepository extends JpaRepository<SingoloOrdine, Integer> {
    List<SingoloOrdine> findAllByIdOrdinazione(int idOrdinazione);

}
