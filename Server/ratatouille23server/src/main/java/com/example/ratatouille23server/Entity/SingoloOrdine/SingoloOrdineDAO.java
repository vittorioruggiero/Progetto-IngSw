package com.example.ratatouille23server.Entity.SingoloOrdine;

import com.example.ratatouille23server.Controller.SingoloOrdineController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SingoloOrdineDAO {

    @Autowired
    private SingoloOrdineRepository repository;

    public SingoloOrdine save(SingoloOrdine singoloOrdine){
        return repository.save(singoloOrdine);
    }

    public List<SingoloOrdine> getAll(){
        /*List<SingoloOrdine> singoliOrdini = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(singoliOrdini::add);
        return singoliOrdini;*/
        return repository.findAll();
    }


    public void delete(SingoloOrdine singoloOrdine){
        repository.delete(singoloOrdine);
    }

    public void deleteById(Integer singoloOrdinePkey){
        repository.deleteById(singoloOrdinePkey);
    }


    public Optional<SingoloOrdine> getById(Integer singoloOrdinePkey){
        return repository.findById(singoloOrdinePkey);
    }

    public ResponseEntity<List<SingoloOrdine>> getAllByOrdinazione(int idOrdinazione) {
        List<SingoloOrdine> listaSingoliOrdini;
        try {
            listaSingoliOrdini = repository.findAllByIdOrdinazione(idOrdinazione);
            return new ResponseEntity<>(listaSingoliOrdini, HttpStatus.OK);
        }
        catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public List<SingoloOrdine> saveAll(List<SingoloOrdine> prodottiOrdine) {
        return repository.saveAll(prodottiOrdine);

    }
}
