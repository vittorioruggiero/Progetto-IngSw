package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaDAO;
import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddettoSalaController {

    @Autowired
    private AddettoSalaDAO addettoSalaDAO;

    @GetMapping("/addettosala/get-all")
    public List<AddettoSala> getAllAddettoSala(){
        return addettoSalaDAO.getAll();
    }

    @RequestMapping(value = "/addettosala/get-by-id", method = RequestMethod.GET)
    public Optional<AddettoSala> getAddettoSalaById(@RequestParam("email") String email){
        return addettoSalaDAO.getById(email);
    }

    @RequestMapping(value = "/addettosala/get-by-username", method = RequestMethod.GET)
    public ResponseEntity<AddettoSala> getAddettoSalaByUsername(@RequestParam("username") String username) {
        return addettoSalaDAO.getByNomeUtente(username);
    }

    @PostMapping("/addettosala/save")
    public AddettoSala save(@RequestBody AddettoSala addettoSala){
        return addettoSalaDAO.save(addettoSala);
    }


}
