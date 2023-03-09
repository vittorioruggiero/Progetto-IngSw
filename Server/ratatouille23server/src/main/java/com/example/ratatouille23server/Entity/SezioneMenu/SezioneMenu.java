package com.example.ratatouille23server.Entity.SezioneMenu;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;

@Entity
@Table(name = "sezionemenu")
public class SezioneMenu {

    @Id
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome", foreignKey = @ForeignKey(name = "attivita_fkey"))
    @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo", foreignKey = @ForeignKey(name = "attivita_fkey"))
    private Attivita attivita;

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
