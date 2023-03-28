package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Amministratore.AmministratoreDAO;
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

    @RequestMapping(value = "/admin/get-by-id", method = RequestMethod.GET)
    public Optional<Amministratore> getAdminById(@RequestParam("email") String email){
        return amministratoreDAO.getById(email);
    }

    @RequestMapping(value = "/admin/get-by-username", method = RequestMethod.GET)
    public ResponseEntity<Amministratore> getAdminByUsername(@RequestParam("username") String username){
        return amministratoreDAO.getByNomeUtente(username);
    }

    @PostMapping("/admin/salvataggioAdmin")
    public Amministratore salvataggioAdmin(@RequestBody Amministratore amministratore){
        return amministratoreDAO.salvataggioAdmin(amministratore);
    }
}
