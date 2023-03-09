package com.example.ratatouille23server.Entity.AddettoSala;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;

@Entity
@Table(name = "addettosala", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente", columnNames = "nomeUtente"))
public class AddettoSala {

    @Id
    private String email;
    private String nomeUtente;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome", foreignKey = @ForeignKey(name = "attivita_fkey"))
    @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo", foreignKey = @ForeignKey(name = "attivita_fkey"))
    private Attivita attivita;

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
        return "AddettoSala{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
