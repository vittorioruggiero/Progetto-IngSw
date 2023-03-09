package com.example.ratatouille23server.Entity.Avviso;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvvisoDAO {

    @Autowired
    private AvvisoRepository repository;

    public Avviso save(Avviso avviso){
        return repository.save(avviso);
    }

    public List<Avviso> getAll(){
        List<Avviso> avvisoList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(avvisoList::add);
        return avvisoList;
    }


    public void delete(Avviso avviso){
        repository.delete(avviso);
    }

}
