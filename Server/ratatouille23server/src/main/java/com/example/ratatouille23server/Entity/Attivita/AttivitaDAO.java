package com.example.ratatouille23server.Entity.Attivita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttivitaDAO {

    @Autowired
    private AttivitaRepository repository;

    public Attivita save(Attivita attivita){
        return repository.save(attivita);
    }

    public List<Attivita> getAll(){
        List<Attivita> attivitaList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(attivitaList::add);
        return attivitaList;
    }


    public void delete(Attivita attivita){
        repository.delete(attivita);
    }

    public void deleteById(AttivitaPkey attivitaPkey){
        repository.deleteById(attivitaPkey);
    }

    public Optional<Attivita> getById(AttivitaPkey attivitaPkey){
        return repository.findById(attivitaPkey);
    }

}
