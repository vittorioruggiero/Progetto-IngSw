package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AttivitaController {

    @Autowired
    private AttivitaDAO attivitaDAO;

    @GetMapping("/attivita/get-all")
    public List<Attivita> getAllAttivita(){
        return attivitaDAO.getAll();
    }

    @RequestMapping(value = "/attivita/get-by-id", method = RequestMethod.GET)
    public Optional<Attivita> getAdminById(@RequestParam("id") int id){
        return attivitaDAO.getById(id);
    }

    @PostMapping("/attivita/save")
    public Attivita save(@RequestBody Attivita attivita){
        return attivitaDAO.save(attivita);
    }

    @PostMapping("/attivita/delete")
    public void delete(@RequestBody Attivita attivita){
        attivitaDAO.delete(attivita);
    }

    @PostMapping("/attivita/delete-by-id")
    public void deleteById(@RequestParam("id") int id){
        attivitaDAO.deleteById(id);
    }

    @PostMapping("/attivita/update-by-id")
    public Attivita updateById(@RequestParam("id")int id,
                               @RequestParam("nome") String nome,
                               @RequestParam("indirizzo") String indirizzo,
                               @RequestParam("telefono") String telefono,
                               @RequestParam("capienza") int capienza){
        return attivitaDAO.updateById(id, nome, indirizzo, telefono, capienza);
    }

}
