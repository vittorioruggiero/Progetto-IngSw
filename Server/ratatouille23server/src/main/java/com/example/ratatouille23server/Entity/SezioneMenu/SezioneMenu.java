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

    @Column(name = "idAttivita")
    private int idAttivita = 0;
    /*@Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "sezionemenu_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey"), insertable = false, updatable = false)
    })*/
    private Attivita attivitaSezione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sezioneMenu")
    private List<ProdottoMenu> prodottiMenu;

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "SezioneMenu{" +
                "nome='" + nome + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                '}';
    }
}
