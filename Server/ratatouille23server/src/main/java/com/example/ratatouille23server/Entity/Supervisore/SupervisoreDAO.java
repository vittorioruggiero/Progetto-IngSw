package com.example.ratatouille23server.Entity.Supervisore;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupervisoreDAO {

    @Autowired
    private SupervisoreRepository repository;

    public Supervisore salvataggioSupervisore(Supervisore supervisore){
        return repository.save(supervisore);
    }

    public List<Supervisore> getAll(){
        List<Supervisore> supervisoriList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(supervisoriList::add);
        return supervisoriList;
    }

    public void delete(Supervisore supervisore){
        repository.delete(supervisore);
    }

    public void deleteById(String supervisorePkey){
        repository.deleteById(supervisorePkey);
    }

    public Optional<Supervisore> getById(String supervisorePkey){
        return repository.findById(supervisorePkey);
    }

}
