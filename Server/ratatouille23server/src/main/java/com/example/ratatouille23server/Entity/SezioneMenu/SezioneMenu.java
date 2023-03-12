package com.example.ratatouille23server.Entity.SezioneMenu;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sezionemenu")
public class SezioneMenu {

    @Id
    private String nome;
    @Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey"), insertable = false, updatable = false)
    })
    private Attivita attivitaSezione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sezioneMenu")
    private List<ProdottoMenu> prodottiMenu;

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

    public Attivita getAttivitaSezione() {
        return attivitaSezione;
    }

    public void setAttivitaSezione(Attivita attivitaSezione) {
        this.attivitaSezione = attivitaSezione;
    }

    @Override
    public String toString() {
        return "SezioneMenu{" +
                "nome='" + nome + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                '}';
    }
}
