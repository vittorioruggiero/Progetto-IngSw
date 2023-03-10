package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SingoloOrdineController {

    @Autowired
    private SingoloOrdineDAO singoloOrdineDAO;

    @GetMapping("/singoloOrdine/get-all")
    public List<SingoloOrdine> getAllAttivita(){
        return singoloOrdineDAO.getAll();
    }

    @PostMapping("/singoloOrdine/save")
    public SingoloOrdine save(@RequestBody SingoloOrdine singoloOrdine){
        return singoloOrdineDAO.save(singoloOrdine);
    }

}
