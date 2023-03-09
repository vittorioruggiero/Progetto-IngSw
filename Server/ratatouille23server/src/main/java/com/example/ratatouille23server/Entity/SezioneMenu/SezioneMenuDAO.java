package com.example.ratatouille23server.Entity.SezioneMenu;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
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

        public List<SezioneMenu> getAll(){
                List<SezioneMenu> sezioniMenu = new ArrayList<>();
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

}
