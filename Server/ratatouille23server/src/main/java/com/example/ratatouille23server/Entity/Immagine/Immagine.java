package com.example.ratatouille23server.Entity.Immagine;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;

@Entity
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String uri;

    @Column(name = "idAttivita")
    private int idAttivita = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "immagine_attivita_fkey"), insertable = false, updatable = false)
    private Attivita attivitaImmagine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    @Override
    public String toString() {
        return "Immagine{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", idAttivita=" + idAttivita +
                '}';
    }
}
