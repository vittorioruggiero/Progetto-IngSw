package com.example.ratatouille23server.Entity.Attivita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttivitaDAO {

    @Autowired
    private AttivitaRepository repository;

    public void save(Attivita attivita){
        repository.save(attivita);
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

    public void deleteById(AttivitaPkey attivitapkey){
        repository.deleteById(attivitapkey);
    }


}
