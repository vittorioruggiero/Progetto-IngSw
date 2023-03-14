package com.example.ratatouille23server.Entity.Amministratore;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AmministratoreDAO {

    @Autowired
    private AmministratoreRepository repository;

    public Amministratore salvataggioAdmin(Amministratore amministratore){
        return repository.save(amministratore);
    }

    public void delete(Amministratore amministratore){
        repository.delete(amministratore);
    }

    public List<Amministratore> getAll(){
        List<Amministratore> amministratoriList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(amministratoriList::add);
        return amministratoriList;
    }

    public Optional<Amministratore> getById(String email){
        return repository.findById(email);
    }


    public ResponseEntity<Amministratore> getByNomeUtente(String nomeUtente){
        Amministratore amministratore;
        try{
            amministratore = repository.findByNomeUtente(nomeUtente);
            return new ResponseEntity<>(amministratore, HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
