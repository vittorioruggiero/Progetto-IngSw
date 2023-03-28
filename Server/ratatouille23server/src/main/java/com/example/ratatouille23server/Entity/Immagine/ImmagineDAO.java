package com.example.ratatouille23server.Entity.Immagine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class ImmagineDAO {

    @Autowired
    ImmagineRepository repository;

    public Immagine salvaImmagine(String uri, int idAttivita) {

        Immagine immagine;
        if(repository.findByIdAttivita(idAttivita) != null){
            immagine = repository.findByIdAttivita(idAttivita);
            immagine.setUri(uri);
        }else{
            immagine = new Immagine();
            immagine.setUri(uri);
            immagine.setIdAttivita(idAttivita);

        }

        return repository.save(immagine);

    }

    public Immagine findByIdAttivita(int idAttivita){
        try{
            return repository.findByIdAttivita(idAttivita);
        }catch(NoSuchElementException e){
            return null;
        }

    }
}
