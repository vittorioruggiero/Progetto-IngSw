package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaDAO;
import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Amministratore.AmministratoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AmministratoreController {

    @Autowired
    private AmministratoreDAO amministratoreDAO;

    @GetMapping("/amministratore/get-all")
    public List<Amministratore> getAllAmministratore(){
        return amministratoreDAO.getAll();
    }

    @PostMapping("/amministratore/save")
    public Amministratore save(@RequestBody Amministratore amministratore){
        return amministratoreDAO.save(amministratore);
    }
}
