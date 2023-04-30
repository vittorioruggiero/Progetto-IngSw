package com.example.ratatouille23server.Entity.Ordinazione;

import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        /*List<Ordinazione> ordinazioniList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(ordinazioniList::add);
        return ordinazioniList;*/
        return repository.findAll();
    }

    public void delete(Ordinazione ordinazione){
        repository.delete(ordinazione);
    }

    public void deleteById(Integer ordinazionePkey){
        repository.deleteById(ordinazionePkey);
    }

    @Transactional
    public void deleteConCampi(int numeroTavolo, int idAttivita) {
        repository.deleteByNumeroTavoloAndIdAttivita(numeroTavolo, idAttivita);
    }

    public Optional<Ordinazione> getById(Integer ordinazionePkey){
        return repository.findById(ordinazionePkey);
    }

    public ResponseEntity<List<Ordinazione>> getAllByAttivita(int idAttivita) {
        List<Ordinazione> listaOrdinazioni;
        try {
            listaOrdinazioni = repository.findAllByIdAttivita(idAttivita);
            return new ResponseEntity<>(listaOrdinazioni, HttpStatus.OK);
        }
        catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Ordinazione> getByTavolo(int idAttivita, int numeroTavolo){
        Ordinazione ordinazione;
        try{
            ordinazione = repository.findByIdAttivitaAndNumeroTavolo(idAttivita, numeroTavolo);
            return new ResponseEntity<>(ordinazione, HttpStatus.OK);
        }catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public Ordinazione saveConCampi(int numeroTavolo, int numeroCommensali, int idAttivita) {

        Ordinazione ordinazione = new Ordinazione();
        ordinazione.setNumeroTavolo(numeroTavolo);
        ordinazione.setNumeroCommensali(numeroCommensali);
        ordinazione.setIdAttivita(idAttivita);
        return repository.save(ordinazione);

    }
}
