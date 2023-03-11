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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey")),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "sezione_attivita_fkey"))
    })
    private Attivita attivitaSezione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sezioneMenu")
    private List<ProdottoMenu> prodottiMenu;

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
                '}';
    }
}
