package com.example.ratatouille23server.Entity.Conto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conto;
    private Date data;
    private Double importo;
    private Boolean stato;

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Conto{" +
                "id_conto=" + id_conto +
                ", data=" + data +
                ", importo=" + importo +
                ", stato=" + stato +
                '}';
    }
}
