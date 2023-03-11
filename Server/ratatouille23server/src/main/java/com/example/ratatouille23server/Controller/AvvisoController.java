package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import com.example.ratatouille23server.Entity.Avviso.Avviso;
import com.example.ratatouille23server.Entity.Avviso.AvvisoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/avviso/save")
    public Avviso save(@RequestBody Avviso avviso){
        return avvisoDAO.save(avviso);
    }

}
