package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SezioneMenuController {

    @Autowired
    private SezioneMenuDAO sezioneMenuDAO;

    @GetMapping("/sezioneMenu/get-all")
    public List<SezioneMenu> getAllSezioneMenu(){
        return sezioneMenuDAO.getAll();
    }

    @PostMapping("/sezioneMenu/save")
    public SezioneMenu save(@RequestBody SezioneMenu sezioneMenu){
        return sezioneMenuDAO.save(sezioneMenu);
    }

}
