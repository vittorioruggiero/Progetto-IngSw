package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/attivita/get-by-id", method = RequestMethod.GET)
    public Optional<Attivita> getAdminById(@RequestParam("nome") String nome, @RequestParam("indirizzo") String indirizzo){
        return attivitaDAO.getById(nome, indirizzo);
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
