package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import com.example.ratatouille23server.Entity.Avviso.Avviso;
import com.example.ratatouille23server.Entity.Avviso.AvvisoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AvvisoController {

    @Autowired
    private AvvisoDAO avvisoDAO;

    @GetMapping("/avviso/get-all")
    public List<Avviso> getAllAvviso(){
        return avvisoDAO.getAll();
    }

    @GetMapping("/avviso/get-by-id")
    public Optional<Avviso> getAttivitaById(@RequestBody Integer avvisopkey){
        return avvisoDAO.getById(avvisopkey);
    }

    @RequestMapping(value = "/avviso/get-all-by-attivita", method = RequestMethod.GET)
    public ResponseEntity<List<Avviso>> getAllAvvisiByAttivita(@RequestParam("nomeAttivita") String nomeAttivita, @RequestParam("indirizzoAttivita") String indirizzoAttivita) {
        return avvisoDAO.getAllByAttivita(nomeAttivita, indirizzoAttivita);
    }

    @PostMapping("/avviso/salvataggioAvviso")
    public Avviso salvataggioAvviso(@RequestBody Avviso avviso){
        return avvisoDAO.salvataggioAvviso(avviso);
    }

}
