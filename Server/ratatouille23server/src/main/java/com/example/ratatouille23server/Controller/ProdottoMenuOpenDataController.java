package com.example.ratatouille23server.Controller;

import com.example.ratatouille23server.Entity.ProdottoMenuOpenData.ProdottoMenuOpenData;
import com.example.ratatouille23server.Entity.ProdottoMenuOpenData.ProdottoMenuOpenDatoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdottoMenuOpenDataController {

    @Autowired
    private ProdottoMenuOpenDatoDAO prodottoMenuOpenDatoDAO;

    @GetMapping("/prodottoMenuOpenData/get-all")
    public List<ProdottoMenuOpenData> getAllProdottoMenuOpenData(){
        return prodottoMenuOpenDatoDAO.getAll();
    }

    @RequestMapping(value = "/prodottoMenuOpenData/get-by-nome", method = RequestMethod.GET)
    public ProdottoMenuOpenData getProdottoByNome(@RequestParam("nome") String nome){
        return prodottoMenuOpenDatoDAO.findByNome(nome);
    }

}
