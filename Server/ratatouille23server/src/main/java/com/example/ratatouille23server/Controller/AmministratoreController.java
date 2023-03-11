package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaDAO;
import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Amministratore.AmministratoreDAO;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AmministratoreController {

    @Autowired
    private AmministratoreDAO amministratoreDAO;

    @GetMapping("/admin/get-all")
    public List<Amministratore> getAllAmministratore(){
        return amministratoreDAO.getAll();
    }

    @GetMapping("/admin/get-by-id")
    public Optional<Amministratore> getAdminById(@RequestParam(required = true) String email){
        return amministratoreDAO.getById(email);
    }

    @GetMapping("/admin/get-by-username")
    public ResponseEntity<Amministratore> getAdminByUsername(@RequestParam(required = true) String username){
        return amministratoreDAO.getByNomeUtente(username);
    }

    @PostMapping("/admin/salvataggioAdmin")
    public Amministratore salvataggioAdmin(@RequestBody Amministratore amministratore){
        return amministratoreDAO.salvataggioAdmin(amministratore);
    }
}
