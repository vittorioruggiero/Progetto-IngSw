package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SezioneMenuController {

    @Autowired
    private SezioneMenuDAO sezioneMenuDAO;

    @GetMapping("/sezioneMenu/get-all")
    public ArrayList<SezioneMenu> getAllSezioneMenu(){
        return sezioneMenuDAO.getAll();
    }

    @RequestMapping(value = "/sezioneMenu/get-by-attivita", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<SezioneMenu>> getSezioniByAttivita(@RequestParam("nomeAttivita") String nomeAttivita, @RequestParam("indirizzoAttivita") String indirizzoAttivita){
        return sezioneMenuDAO.findByAttivita(nomeAttivita, indirizzoAttivita);
    }

    @PostMapping("/sezioneMenu/save")
    public SezioneMenu save(@RequestBody SezioneMenu sezioneMenu){
        return sezioneMenuDAO.save(sezioneMenu);
    }

    @RequestMapping(value = "/sezioneMenu/delete-by-id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("nome") String nome){
        sezioneMenuDAO.deleteById(nome);
    }

}
