package com.example.ratatouille23server.Entity.Supervisore;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "supervisore", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente_supervisore", columnNames = "nomeUtente"))
public class Supervisore {

    @Id
    private String email;
    private String nomeUtente;
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "supervisore_attivita_fkey")),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "supervisore_attivita_fkey"))
    })
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

    @Override
    public String toString() {
        return "Supervisore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
