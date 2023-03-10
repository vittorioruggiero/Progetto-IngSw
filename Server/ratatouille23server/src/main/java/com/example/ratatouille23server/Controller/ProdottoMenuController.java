package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdottoMenuController {

    @Autowired
    private ProdottoMenuDAO prodottoMenuDAO;

    @GetMapping("/prodottoMenu/get-all")
    public List<ProdottoMenu> getAllAttivita(){
        return prodottoMenuDAO.getAll();
    }

    @PostMapping("/prodottoMenu/save")
    public ProdottoMenu save(@RequestBody ProdottoMenu prodottoMenu){
        return prodottoMenuDAO.save(prodottoMenu);
    }

}
