package com.example.ratatouille23server.Entity.Conto;

import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conto;
    @Column(nullable = false)
    private java.sql.Date data;
    @Column(nullable = false)
    private Double importo;
    @Column(nullable = false)
    private Boolean stato;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conto")
    private Ordinazione ordinazione;

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }

    public java.sql.Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
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
