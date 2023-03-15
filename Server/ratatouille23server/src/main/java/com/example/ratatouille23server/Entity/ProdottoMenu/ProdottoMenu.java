package com.example.ratatouille23server.Entity.ProdottoMenu;

import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "prodottomenu", uniqueConstraints = @UniqueConstraint(name = "unique_nome_seconda_lingua", columnNames = "nomeSecondaLingua"))
public class ProdottoMenu {

    @Id
    private String nomeProdotto;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private double costo;
    private String nomeSecondaLingua;
    private String descrizioneSecondaLingua;
    private String allergeni;
    @Column(name = "nome_sezione", nullable = false)
    private String nomeSezione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nome_sezione", referencedColumnName = "nome",
            foreignKey = @ForeignKey(name = "sezione_fkey"), insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SezioneMenu sezioneMenu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prodottoMenuSingoloOrdine")
    List<SingoloOrdine> singoliOrdiniProdotti;

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNomeSecondaLingua() {
        return nomeSecondaLingua;
    }

    public void setNomeSecondaLingua(String nomeSecondaLingua) {
        this.nomeSecondaLingua = nomeSecondaLingua;
    }

    public String getDescrizioneSecondaLingua() {
        return descrizioneSecondaLingua;
    }

    public void setDescrizioneSecondaLingua(String descrizioneSecondaLingua) {
        this.descrizioneSecondaLingua = descrizioneSecondaLingua;
    }

    public String getAllergeni() {
        return allergeni;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getNomeSezione() {
        return nomeSezione;
    }

    public void setNomeSezione(String nomeSezione) {
        this.nomeSezione = nomeSezione;
    }


    @Override
    public String toString() {
        return "ProdottoMenu{" +
                "nome='" + nomeProdotto + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", costo=" + costo +
                ", nomeSecondaLingua='" + nomeSecondaLingua + '\'' +
                ", descrizioneSecondaLingua='" + descrizioneSecondaLingua + '\'' +
                ", allergeni='" + allergeni + '\'' +
                '}';
    }
}
