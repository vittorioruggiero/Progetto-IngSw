package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.Ordinazione.OrdinazioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdinazioneController {

    @Autowired
    private OrdinazioneDAO ordinazioneDAO;

    @GetMapping("/ordinazione/get-all")
    public List<Ordinazione> getAllOrdinazione(){
        return ordinazioneDAO.getAll();
    }

    @RequestMapping(value = "/ordinazione/get-all-by-attivita", method = RequestMethod.GET)
    public ResponseEntity<List<Ordinazione>> getAllOrdinazioniByAttivita(@RequestParam("nomeAttivita") String nomeAttivita, @RequestParam("indirizzoAttivita") String indirizzoAttivita) {
        return ordinazioneDAO.getAllByAttivita(nomeAttivita, indirizzoAttivita);
    }

    @PostMapping("/ordinazione/save")
    public Ordinazione save(@RequestBody Ordinazione ordinazione){
        return ordinazioneDAO.save(ordinazione);
    }

}
