package com.example.ratatouille23server.Entity.Conto;

import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.AddettoSala.AddettoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContoDAO {

    @Autowired
    private ContoRepository repository;

    public Conto save(Conto conto){
        return repository.save(conto);
    }

    public List<Conto> getAll(){
        List<Conto> contiList = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(contiList::add);
        return contiList;
    }

    public void delete(Conto conto){
        repository.delete(conto);
    }

    public void deleteById(Integer contoPkey){
        repository.deleteById(contoPkey);
    }

    public Optional<Conto> getById(Integer contoPkey){
        return repository.findById(contoPkey);
    }

    public Conto saveByImporto(Double importo, int id){
        for(Conto conto : repository.findAll()){
            if(conto.getId_conto() == id){
                conto.setImporto(importo);
                return repository.save(conto);
            }
        }
        return null;
    }

}
