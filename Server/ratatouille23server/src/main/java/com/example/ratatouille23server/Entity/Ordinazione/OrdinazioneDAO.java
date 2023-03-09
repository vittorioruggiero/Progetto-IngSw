package com.example.ratatouille23server.Entity.Ordinazione;

import com.example.ratatouille23server.Entity.Conto.Conto;
import com.example.ratatouille23server.Entity.Conto.ContoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdinazioneDAO {

    @Autowired
    private OrdinazioneRepository repository;

    public Ordinazione save(Ordinazione ordinazione){
        return repository.save(ordinazione);
    }

    public List<Ordinazione> getAll(){
        List<Ordinazione> ordinazioniList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(ordinazioniList::add);
        return ordinazioniList;
    }

    public void delete(Ordinazione ordinazione){
        repository.delete(ordinazione);
    }

    public void deleteById(Integer ordinazionePkey){
        repository.deleteById(ordinazionePkey);
    }

    public Optional<Ordinazione> getById(Integer ordinazionePkey){
        return repository.findById(ordinazionePkey);
    }

}
