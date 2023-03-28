package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Conto.Conto;
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

    @RequestMapping(value = "/ordinazione/get-by-tavolo", method = RequestMethod.GET)
    public ResponseEntity<Ordinazione> getOrdinazioneByTavolo(@RequestParam("idAttivita") int idAttivita,
                                                                    @RequestParam("numeroTavolo") int numeroTavolo) {
        return ordinazioneDAO.getByTavolo(idAttivita, numeroTavolo);
    }

    @PostMapping("/ordinazione/save")
    public Ordinazione save(@RequestBody Ordinazione ordinazione){
        return ordinazioneDAO.save(ordinazione);
    }

    @PostMapping("/ordinazione/save-con-campi")
    public Ordinazione save(@RequestParam("numeroTavolo") int numeroTavolo,
                      @RequestParam("numeroCommensali") int numeroCommensali,
                      @RequestParam("idAttivita") int idAttivita){
        return ordinazioneDAO.saveConCampi(numeroTavolo, numeroCommensali, idAttivita);
    }

    @RequestMapping(value = "/ordinazione/delete-by-id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id_ordinazione") int id_ordinazione){
        ordinazioneDAO.deleteById(id_ordinazione);
    }



}
