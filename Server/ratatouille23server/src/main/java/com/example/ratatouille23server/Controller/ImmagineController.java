package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Immagine.Immagine;
import com.example.ratatouille23server.Entity.Immagine.ImmagineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImmagineController {

    @Autowired
    private ImmagineDAO immagineDao;

    @PostMapping("/immagine/salva")
    public Immagine salvaImmagine(@RequestParam("uri") String uri,
                                  @RequestParam("idAttivita") int idAttivita){
        return immagineDao.salvaImmagine(uri, idAttivita);
    }

    @GetMapping("/immagine/get-by-id-attivita")
    public Immagine getByIdAttivita(@RequestParam("idAttivita") int idAttivita){
        return immagineDao.findByIdAttivita(idAttivita);
    }

}
