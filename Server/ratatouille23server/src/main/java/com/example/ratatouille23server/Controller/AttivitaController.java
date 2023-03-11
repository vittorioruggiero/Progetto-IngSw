package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AttivitaController {

    @Autowired
    private AttivitaDAO attivitaDAO;

    @GetMapping("/attivita/get-all")
    public List<Attivita> getAllAttivita(){
        return attivitaDAO.getAll();
    }

    @GetMapping("/attivita/get-by-id")
    public Optional<Attivita> getAttivitaById(@RequestBody AttivitaPkey attivitaPkey){
        return attivitaDAO.getById(attivitaPkey);
    }

    @PostMapping("/attivita/save")
    public Attivita save(@RequestBody Attivita attivita){
        return attivitaDAO.save(attivita);
    }

    @PostMapping("/attivita/delete")
    public void delete(@RequestBody Attivita attivita){
        attivitaDAO.delete(attivita);
    }

    @PostMapping("/attivita/delete-by-id")
    public void deleteById(@RequestBody AttivitaPkey attivitaPkey){
        attivitaDAO.deleteById(attivitaPkey);
    }

}
