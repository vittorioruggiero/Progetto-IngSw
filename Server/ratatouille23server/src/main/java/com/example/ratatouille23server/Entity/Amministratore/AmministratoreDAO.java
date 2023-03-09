package com.example.ratatouille23server.Entity.Amministratore;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AmministratoreDAO {

    @Autowired
    private AmministratoreRepository repository;

    public Amministratore save(Amministratore amministratore){
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


    public void deleteById(String amministratorePkey){
        repository.deleteById(amministratorePkey);
    }

    public Optional<Amministratore> getById(String amministratorePkey){
        return repository.findById(amministratorePkey);
    }

}
