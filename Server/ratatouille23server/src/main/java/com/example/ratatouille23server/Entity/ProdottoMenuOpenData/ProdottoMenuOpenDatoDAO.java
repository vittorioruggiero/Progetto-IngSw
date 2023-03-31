package com.example.ratatouille23server.Entity.ProdottoMenuOpenData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoMenuOpenDatoDAO {

    @Autowired
    private ProdottoMenuOpenDataRepository repository;


    public List<ProdottoMenuOpenData> getAll(){
        return repository.findAll();
    }

    public ProdottoMenuOpenData findByNome(String nome){
        return repository.findByNome(nome);
    }


}
