package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.Ordinazione.OrdinazioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdinazioneController {

    @Autowired
    private OrdinazioneDAO ordinazioneDAO;

    @GetMapping("/ordinazione/get-all")
    public List<Ordinazione> getAllOrdinazione(){
        return ordinazioneDAO.getAll();
    }

    @PostMapping("/ordinazione/save")
    public Ordinazione save(@RequestBody Ordinazione ordinazione){
        return ordinazioneDAO.save(ordinazione);
    }

}
