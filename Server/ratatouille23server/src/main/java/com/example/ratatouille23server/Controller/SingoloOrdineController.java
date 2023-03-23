package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SingoloOrdineController {

    @Autowired
    private SingoloOrdineDAO singoloOrdineDAO;

    @GetMapping("/singoloOrdine/get-all")
    public List<SingoloOrdine> getAllSingoloOrdine(){
        return singoloOrdineDAO.getAll();
    }

    @RequestMapping(value = "/singoloOrdine/get-all-by-ordinazione", method = RequestMethod.GET)
    public ResponseEntity<List<SingoloOrdine>> getAllSingoliOrdiniByOrdinazione(@RequestParam("idOrdinazione") int idOrdinazione) {
        return singoloOrdineDAO.getAllByOrdinazione(idOrdinazione);
    }

    @PostMapping("/singoloOrdine/save")
    public SingoloOrdine save(@RequestBody SingoloOrdine singoloOrdine){
        return singoloOrdineDAO.save(singoloOrdine);
    }

    @PostMapping("/singoloOrdine/save-all")
    public List<SingoloOrdine> saveAll(@RequestBody List<SingoloOrdine> prodottiOrdine){
        return singoloOrdineDAO.saveAll(prodottiOrdine);
    }

}
