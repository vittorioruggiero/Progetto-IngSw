package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Amministratore.AmministratoreDAO;
import com.example.ratatouille23server.Entity.Conto.Conto;
import com.example.ratatouille23server.Entity.Conto.ContoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContoController {

    @Autowired
    private ContoDAO contoDAO;

    @GetMapping("/conto/get-all")
    public List<Conto> getAllConto(){
        return contoDAO.getAll();
    }

    @PostMapping("/conto/save")
    public Conto save(@RequestBody Conto conto){
        return contoDAO.save(conto);
    }

    @PostMapping("/conto/save-importo")
    public Conto save(@RequestParam("importo") Double importo, @RequestParam("id") int id){
        return contoDAO.saveByImporto(importo, id);
    }

}
