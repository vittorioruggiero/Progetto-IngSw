package com.example.ratatouille23server.Entity.Avviso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvvisoDAO {

    @Autowired
    private AvvisoRepository repository;

    public Avviso salvataggioAvviso(Avviso avviso){
        return repository.save(avviso);
    }

    public List<Avviso> getAll(){
        List<Avviso> avvisoList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(avvisoList::add);
        return avvisoList;
    }

    public ResponseEntity<List<Avviso>> getAllByAttivita(int idAttivita) {
        List<Avviso> listaAvvisi;
        try {
            listaAvvisi = repository.findAllByIdAttivita(idAttivita);
            return new ResponseEntity<>(listaAvvisi, HttpStatus.OK);
        }
        catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<Avviso>> getAllByEmail(String email){
        List<Avviso> listaAvvisi;
        try{
            listaAvvisi = repository.findAllByEmailOrderByIdDesc(email);
            return new ResponseEntity<>(listaAvvisi, HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public void delete(Avviso avviso){
        repository.delete(avviso);
    }

    public Optional<Avviso> getById(Integer avvisoPkey){
        return repository.findById(avvisoPkey);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }


}
