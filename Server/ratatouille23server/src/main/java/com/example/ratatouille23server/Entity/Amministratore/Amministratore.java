package com.example.ratatouille23server.Entity.Amministratore;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;
import org.hibernate.engine.internal.ForeignKeys;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Table(name = "amministratore", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente_admin", columnNames = "nomeUtente"))
public class Amministratore {

    @Id
    private String email;
    @Column(nullable = false)
    private String nomeUtente;
    @Column(nullable = false)
    private String password;
    @Column(name = "idAttivita")
    private int idAttivita = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "admin_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "admin_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "admin_attivita_fkey"), insertable = false, updatable = false)
    })*/
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

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    @Override
    public String toString() {
        return "Amministratore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                '}';
    }
}
