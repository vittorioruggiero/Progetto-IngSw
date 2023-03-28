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
    @Column(name = "idAttivita")
    private int idAttivita = 0;
    /*@Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ordinazione_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "ordinazione_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "ordinazione_attivita_fkey"), insertable = false, updatable = false)
    })*/
    private Attivita attivitaOrdinazione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ordinazioneSingoloOrdine")
    List<SingoloOrdine> singoliOrdiniOrdinazione;

    public void setId_ordinazione(int id_ordinazione) {
        this.id_ordinazione = id_ordinazione;
    }

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

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    @Override
    public String toString() {
        return "Ordinazione{" +
                "id_ordinazione=" + id_ordinazione +
                ", numeroTavolo=" + numeroTavolo +
                ", numeroCommensali=" + numeroCommensali +
                ", idAttivita='" + idAttivita + '\'' +
                '}';
    }
}
