package com.example.ratatouille23server.Entity.Avviso;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Attivita.AttivitaPkey;
import jakarta.persistence.*;

@Entity
public class Avviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String avviso;
    @Column(name = "idAttivita")
    private int idAttivita = 0;
    /*@Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;*/
    @Column(name = "email")
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "avviso_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "avviso_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "avviso_attivita_fkey"), insertable = false, updatable = false)
    })*/
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

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Avviso{" +
                "id=" + id +
                ", avviso='" + avviso + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
