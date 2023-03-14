package com.example.ratatouille23server.Entity.AddettoSala;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddettoSalaDAO {

    @Autowired
    private AddettoSalaRepository repository;

    public AddettoSala save(AddettoSala addettoSala){
        return repository.save(addettoSala);
    }

    public List<AddettoSala> getAll(){
        List<AddettoSala> addettiSalaList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(addettiSalaList::add);
        return addettiSalaList;
    }

    public void delete(AddettoSala addettoSala){
        repository.delete(addettoSala);
    }

    public void deleteById(String addettoSalaPkey){
        repository.deleteById(addettoSalaPkey);
    }

    public Optional<AddettoSala> getById(String addettoSalaPkey){
        return repository.findById(addettoSalaPkey);
    }

    public ResponseEntity<AddettoSala> getByNomeUtente(String nomeUtente){
        AddettoSala addettoSala;
        try{
            addettoSala = repository.findByNomeUtente(nomeUtente);
            return new ResponseEntity<>(addettoSala, HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
