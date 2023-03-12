package com.example.ratatouille23server.Entity.Ordinazione;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Conto.Conto;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordinazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ordinazione;
    @Column(nullable = false)
    private int numeroTavolo;
    @Column(nullable = false)
    private int numeroCommensali;
    @Column(name = "id_conto")
    private int id_conto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conto", referencedColumnName = "id_conto",
            foreignKey = @ForeignKey(name = "conto_fkey"), insertable = false, updatable = false)
    private Conto conto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ordinazioneSingoloOrdine")
    List<SingoloOrdine> singoliOrdiniOrdinazione;

    public int getId_ordinazione() {
        return id_ordinazione;
    }

    public int getNumeroTavolo() {
        return numeroTavolo;
    }

    public void setNumeroTavolo(int numeroTavolo) {
        this.numeroTavolo = numeroTavolo;
    }

    public int getNumeroCommensali() {
        return numeroCommensali;
    }

    public void setNumeroCommensali(int numeroCommensali) {
        this.numeroCommensali = numeroCommensali;
    }

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }

    @Override
    public String toString() {
        return "Ordinazione{" +
                "id_ordinazione=" + id_ordinazione +
                ", numeroTavolo=" + numeroTavolo +
                ", numeroCommensali=" + numeroCommensali +
                ", id_conto=" + id_conto +
                '}';
    }
}
