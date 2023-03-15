package com.example.ratatouille23server.Entity.SezioneMenu;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SezioneMenuDAO{

        @Autowired
        private SezioneMenuRepository repository;

        public SezioneMenu save(SezioneMenu sezioneMenu){
                return repository.save(sezioneMenu);
        }

        public ArrayList<SezioneMenu> getAll(){
                ArrayList<SezioneMenu> sezioniMenu = new ArrayList<>();
                Streamable.of(repository.findAll())
                        .forEach(sezioniMenu::add);
                return sezioniMenu;
        }

        public void delete(SezioneMenu sezioneMenu){
                repository.delete(sezioneMenu);
        }

        public void deleteById(String sezioneMenuPkey){
                repository.deleteById(sezioneMenuPkey);
        }

        public Optional<SezioneMenu> getById(String sezioneMenuPkey){
                return repository.findById(sezioneMenuPkey);
        }

        public ResponseEntity<ArrayList<SezioneMenu>> findByAttivita(String nomeAttivita, String indirizzoAttivita){
                ArrayList<SezioneMenu> sezioni = new ArrayList<>();
                try{
                        for(SezioneMenu sezioneMenu : repository.findAll()){
                                if(sezioneMenu.getNomeAttivita() != null){
                                        if(sezioneMenu.getNomeAttivita().equals(nomeAttivita) && sezioneMenu.getIndirizzoAttivita().equals(indirizzoAttivita)){
                                                sezioni.add(sezioneMenu);
                                        }
                                }

                        }
                        return new ResponseEntity<>(sezioni, HttpStatus.OK);
                }catch(NullPointerException e){
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

        }

}
