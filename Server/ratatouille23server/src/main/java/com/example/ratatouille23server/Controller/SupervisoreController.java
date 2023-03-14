package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Supervisore.Supervisore;
import com.example.ratatouille23server.Entity.Supervisore.SupervisoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SupervisoreController {

    @Autowired
    private SupervisoreDAO supervisoreDAO;

    @GetMapping("/supervisore/get-all")
    public List<Supervisore> getAllSupervisore(){
        return supervisoreDAO.getAll();
    }

    @RequestMapping(value = "/supervisore/get-by-id", method = RequestMethod.GET)
    public Optional<Supervisore> getSupervisoreById(@RequestParam("email") String email){
        return supervisoreDAO.getById(email);
    }

    @RequestMapping(value = "/supervisore/get-by-username", method = RequestMethod.GET)
    public ResponseEntity<Supervisore> getSupervisoreByUsername(@RequestParam("username") String username) {
        return supervisoreDAO.getByNomeUtente(username);
    }

    @PostMapping("/supervisore/salvataggio-supervisore")
    public Supervisore salvataggioSupervisore(@RequestBody Supervisore supervisore){
        return supervisoreDAO.salvataggioSupervisore(supervisore);
    }

}
