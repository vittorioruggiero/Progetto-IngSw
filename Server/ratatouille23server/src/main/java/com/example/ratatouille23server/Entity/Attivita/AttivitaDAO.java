package com.example.ratatouille23server.Entity.Attivita;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AttivitaDAO {

    @Autowired
    private AttivitaRepository repository;

    public Attivita save(Attivita attivita){
        return repository.save(attivita);
    }

    public List<Attivita> getAll(){
        return repository.findAll();
    }


    public void delete(Attivita attivita){
        repository.delete(attivita);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }

    public Optional<Attivita> getById(int id){
        return repository.findById(id);
    }

    public Attivita updateById(int id, String nome, String indirizzo, String telefono, int capienza){


        if(repository.findById(id).get().getId() == 0){
            Attivita attivita = new Attivita();
            attivita.setCapienza(capienza);
            attivita.setIndirizzo(indirizzo);
            attivita.setNome(nome);
            attivita.setTelefono(telefono);
            return repository.save(attivita);
        }else{
            Attivita attivita = repository.findById(id).get();
            attivita.setCapienza(capienza);
            attivita.setIndirizzo(indirizzo);
            attivita.setNome(nome);
            attivita.setTelefono(telefono);
            return repository.save(attivita);
        }


    }

}
