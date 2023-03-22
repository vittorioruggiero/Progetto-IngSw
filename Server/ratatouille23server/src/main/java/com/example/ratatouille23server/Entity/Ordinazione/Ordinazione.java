package com.example.ratatouille23server.Entity.Ordinazione;

import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Attivita.Attivita;
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
    @Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "ordinazione_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "ordinazione_attivita_fkey"), insertable = false, updatable = false)
    })
    private Attivita attivitaOrdinazione;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conto", referencedColumnName = "id_conto",
            foreignKey = @ForeignKey(name = "conto_fkey"), insertable = false, updatable = false)
    private Conto conto;*/

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

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    public String getIndirizzoAttivita() {
        return indirizzoAttivita;
    }

    public void setIndirizzoAttivita(String indirizzoAttivita) {
        this.indirizzoAttivita = indirizzoAttivita;
    }

    @Override
    public String toString() {
        return "Ordinazione{" +
                "id_ordinazione=" + id_ordinazione +
                ", numeroTavolo=" + numeroTavolo +
                ", numeroCommensali=" + numeroCommensali +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                '}';
    }
}
