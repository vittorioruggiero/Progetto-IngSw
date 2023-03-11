package com.example.ratatouille23server.Entity.Avviso;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import jakarta.persistence.*;

@Entity
public class Avviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String avviso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome", foreignKey = @ForeignKey(name = "avviso_attivita_fkey")),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo", foreignKey = @ForeignKey(name = "avviso_attivita_fkey"))
    })
    private Attivita attivitaAvviso;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvviso() {
        return avviso;
    }

    public void setAvviso(String avviso) {
        this.avviso = avviso;
    }

    @Override
    public String toString() {
        return "Avviso{" +
                "id=" + id +
                ", avviso='" + avviso + '\'' +
                '}';
    }
}
