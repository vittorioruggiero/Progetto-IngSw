package com.example.ratatouille23server.Entity.Amministratore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        /*List<Amministratore> amministratoriList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(amministratoriList::add);
        return amministratoriList;*/
        return repository.findAll();
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
