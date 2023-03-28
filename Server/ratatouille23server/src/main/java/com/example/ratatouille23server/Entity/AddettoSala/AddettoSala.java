package com.example.ratatouille23server.Entity.AddettoSala;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "addettosala", uniqueConstraints = @UniqueConstraint(name = "unique_nome_utente_addetto_sala", columnNames = "nomeUtente"))
public class AddettoSala {

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
    @Column(nullable = false, name = "primoAccesso", columnDefinition = "boolean default true")
    private Boolean primoAccesso = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAttivita", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "addettosala_attivita_fkey"), insertable = false, updatable = false)
    /*@JoinColumns({
            @JoinColumn(name = "nomeAttivita", referencedColumnName = "nome",
                    foreignKey = @ForeignKey(name = "addetto_sala_attivita_fkey"), insertable = false, updatable = false),
            @JoinColumn(name = "indirizzoAttivita", referencedColumnName = "indirizzo",
                    foreignKey = @ForeignKey(name = "addetto_sala_attivita_fkey"), insertable = false, updatable = false)
    })*/
    private Attivita attivitaAddettoSala;

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

    public Boolean getPrimoAccesso() {
        return primoAccesso;
    }

    public void setPrimoAccesso(Boolean primoAccesso) {
        this.primoAccesso = primoAccesso;
    }

    @Override
    public String toString() {
        return "AddettoSala{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                ", primoAccesso=" + primoAccesso +
                '}';
    }
}
