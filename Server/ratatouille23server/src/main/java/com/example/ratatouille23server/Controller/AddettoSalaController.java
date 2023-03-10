package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaDAO;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddettoSalaController {

    @Autowired
    private AddettoSalaDAO addettoSalaDAO;

    @GetMapping("/addettosala/get-all")
    public List<AddettoSala> getAllAddettoSala(){
        return addettoSalaDAO.getAll();
    }

    @PostMapping("/addettosala/save")
    public AddettoSala save(@RequestBody AddettoSala addettoSala){
        return addettoSalaDAO.save(addettoSala);
    }


}
