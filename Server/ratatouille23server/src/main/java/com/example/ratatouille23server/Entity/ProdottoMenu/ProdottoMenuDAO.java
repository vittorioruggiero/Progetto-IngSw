package com.example.ratatouille23server.Entity.ProdottoMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoMenuDAO {

    @Autowired
    private ProdottoMenuRepository repository;

    public ProdottoMenu save(ProdottoMenu prodottoMenu){
        return repository.save(prodottoMenu);
    }

    public List<ProdottoMenu> getAll(){
        return repository.findAll();
    }


    public void delete(ProdottoMenu prodottoMenu){
        repository.delete(prodottoMenu);
    }

    public void deleteById(String prodottoMenuPkey){
        repository.deleteById(prodottoMenuPkey);
    }

    public Optional<ProdottoMenu> getById(String prodottoMenuPkey){
        return repository.findById(prodottoMenuPkey);
    }

    public ResponseEntity<List<ProdottoMenu>> findAllBySezione(String nome){
        List<ProdottoMenu> prodottiMenu;
        try{
            prodottiMenu = repository.findAllByNomeSezione(nome);
            return new ResponseEntity<>(prodottiMenu, HttpStatus.OK);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
