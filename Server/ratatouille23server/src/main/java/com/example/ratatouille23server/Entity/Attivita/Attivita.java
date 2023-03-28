package com.example.ratatouille23server.Entity.Attivita;


import com.example.ratatouille23server.Entity.AddettoSala.AddettoSala;
import com.example.ratatouille23server.Entity.Amministratore.Amministratore;
import com.example.ratatouille23server.Entity.Avviso.Avviso;
import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import com.example.ratatouille23server.Entity.Supervisore.Supervisore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "attivita", uniqueConstraints = {
        @UniqueConstraint(name = "unique_nome_attivita", columnNames = "nome"),
        @UniqueConstraint(name = "unique_indirizzo_attivita", columnNames = "indirizzo")
})
//@IdClass(AttivitaPkey.class)
public class Attivita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String indirizzo;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private int capienza;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaAdmin")
    private List<Amministratore> amministratori;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaAvviso")
    private List<Avviso> avvisi;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaSezione")
    private List<SezioneMenu> sezioni;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaAddettoSala")
    private List<AddettoSala> addettiSala;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaSupervisore")
    private List<Supervisore> supervisori;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attivitaOrdinazione")
    private List<Ordinazione> ordinazioni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Attivita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", capienza=" + capienza +
                '}';
    }
}
