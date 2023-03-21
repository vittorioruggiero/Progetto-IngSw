package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdottoMenuController {

    @Autowired
    private ProdottoMenuDAO prodottoMenuDAO;

    @GetMapping("/prodottoMenu/get-all")
    public List<ProdottoMenu> getAllProdottoMenu(){
        return prodottoMenuDAO.getAll();
    }

    @RequestMapping(value = "/prodottoMenu/get-by-id", method = RequestMethod.GET)
    public Optional<ProdottoMenu> getProdottoById(@RequestParam("nome") String nome){
        return prodottoMenuDAO.getById(nome);
    }

    @PostMapping("/prodottoMenu/save")
    public ProdottoMenu save(@RequestBody ProdottoMenu prodottoMenu){
        return prodottoMenuDAO.save(prodottoMenu);
    }

    @RequestMapping(value = "/prodottoMenu/get-by-sezione", method = RequestMethod.GET)
    public ResponseEntity<List<ProdottoMenu>> getProdottiBySezione(@RequestParam("nomeSezione") String nomeSezione){
        return prodottoMenuDAO.findBySezione(nomeSezione);
    }

    @RequestMapping(value = "/prodottoMenu/delete-by-id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("nomeProdotto") String nomeProdotto){
        prodottoMenuDAO.deleteById(nomeProdotto);
    }

}
