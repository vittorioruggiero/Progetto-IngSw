package com.example.ratatouille23server.Entity.SezioneMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SezioneMenuRepository extends JpaRepository<SezioneMenu, String> {

    ArrayList<SezioneMenu> findAllByNomeAttivitaAndIndirizzoAttivita(String nomeAttivita, String indirizzoAttivita);

}
