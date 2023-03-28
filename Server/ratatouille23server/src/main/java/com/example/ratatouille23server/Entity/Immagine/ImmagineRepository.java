package com.example.ratatouille23server.Entity.Immagine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmagineRepository extends JpaRepository<Immagine, Integer> {

    Immagine findByIdAttivita(int idAttivita);

}
