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
    private int numeroTavolo;
    private int numeroCommensali;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conto", referencedColumnName = "id_conto", foreignKey = @ForeignKey(name = "conto_fkey"))
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
}
