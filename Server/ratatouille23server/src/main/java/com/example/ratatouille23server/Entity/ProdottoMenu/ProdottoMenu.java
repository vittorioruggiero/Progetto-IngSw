package com.example.ratatouille23server.Entity.ProdottoMenu;

import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prodottomenu", uniqueConstraints = @UniqueConstraint(name = "unique_nome_seconda_lingua", columnNames = "nomeSecondaLingua"))
public class ProdottoMenu {

    @Id
    private String nomeProdotto;
    private String descrizione;
    private double costo;
    private String nomeSecondaLingua;
    private String descrizioneSecondaLingua;
    private String allergeni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nome_sezione", referencedColumnName = "nome", foreignKey = @ForeignKey(name = "sezione_fkey"))
    private SezioneMenu sezioneMenu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prodottoMenuSingoloOrdine")
    List<SingoloOrdine> singoliOrdiniProdotti;

    public String getNome() {
        return nomeProdotto;
    }

    public void setNome(String nome) {
        this.nomeProdotto = nome;
    }

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
