package com.example.ratatouille23server.Entity.Supervisore;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "supervisore", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente_supervisore", columnNames = "nomeUtente"))
public class Supervisore {

    @Id
    private String email;
    @Column(nullable = false)
    private String nomeUtente;
    @Column(nullable = false)
    private String password;
    @Column(name = "idAttivita")
    private int idAttivita = 0;
    /*@Column(name = "nomeAttivita")
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita")
    private String indirizzoAttivita;*/
    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean primoAccesso = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "supervisore_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "supervisore_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "supervisore_attivita_fkey"), insertable = false, updatable = false)
    })*/
    private Attivita attivitaSupervisore;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPrimoAccesso() {
        return primoAccesso;
    }

    public void setPrimoAccesso(Boolean primoAccesso) {
        this.primoAccesso = primoAccesso;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    @Override
    public String toString() {
        return "Supervisore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                ", primoAccesso=" + primoAccesso +
                '}';
    }
}
