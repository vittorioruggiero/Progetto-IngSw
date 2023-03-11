package com.example.ratatouille23server.Entity.Amministratore;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;

@Entity
@Table(name = "amministratore", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente_admin", columnNames = "nomeUtente"))
public class Amministratore {

    @Id
    private String email;
    private String nomeUtente;
    private String password;
    @Column(name = "nomeAttivita", insertable = false, updatable = false)
    private String nomeAttivita;
    @Column(name = "indirizzoAttivita", insertable = false, updatable = false)
    private String indirizzoAttivita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "admin_attivita_fkey")),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo")
    })
    private Attivita attivitaAdmin;

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

    @Override
    public String toString() {
        return "Amministratore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                '}';
    }
}