package com.example.ratatouille23server.Entity.Ordinazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinazioneRepository extends JpaRepository<Ordinazione, Integer> {
    //List<Ordinazione> findAllByNomeAttivitaAndIndirizzoAttivita(String nomeAttivita, String indirizzoAttivita);

    List<Ordinazione> findAllByIdAttivita(int idAttivita);

    long deleteByNumeroTavoloAndIdAttivita(int numeroTavolo, int idAttivita);

    //Ordinazione findByNomeAttivitaAndIndirizzoAttivitaAndNumeroTavolo(String nomeAttivita, String indirizzoAttivita, int numeroTavolo);

    Ordinazione findByIdAttivitaAndNumeroTavolo(int idAttivita, int numeroTavolo);
}
