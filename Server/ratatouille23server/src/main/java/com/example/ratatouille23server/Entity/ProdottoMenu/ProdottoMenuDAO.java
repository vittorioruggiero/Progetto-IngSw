package com.example.ratatouille23server.Entity.ProdottoMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<ProdottoMenu> prodottiMenu = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(prodottiMenu::add);
        return prodottiMenu;
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

}
