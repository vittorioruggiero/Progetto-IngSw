package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Supervisore.Supervisore;
import com.example.ratatouille23server.Entity.Supervisore.SupervisoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupervisoreController {

    @Autowired
    private SupervisoreDAO supervisoreDAO;

    @GetMapping("/supervisore/get-all")
    public List<Supervisore> getAllAttivita(){
        return supervisoreDAO.getAll();
    }

    @PostMapping("/supervisore/save")
    public Supervisore save(@RequestBody Supervisore supervisore){
        return supervisoreDAO.save(supervisore);
    }

}
