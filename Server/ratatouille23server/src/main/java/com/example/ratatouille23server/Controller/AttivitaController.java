package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttivitaController {

    @Autowired
    private AttivitaDAO attivitaDAO;

    @GetMapping("/attivita/get-all")
    public List<Attivita> getAllAttivita(){
        return attivitaDAO.getAll();
    }

    @PostMapping("/attivita/save")
    public Attivita save(@RequestBody Attivita attivita){
        return attivitaDAO.save(attivita);
    }

}
