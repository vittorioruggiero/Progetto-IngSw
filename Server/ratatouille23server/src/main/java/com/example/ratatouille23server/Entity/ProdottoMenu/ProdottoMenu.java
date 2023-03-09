package com.example.ratatouille23server.Entity.ProdottoMenu;

import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prodottomenu", uniqueConstraints = @UniqueConstraint(name = "unique_nome_seconda_lingua", columnNames = "nomeSecondaLingua"))
public class ProdottoMenu {

    @Id
    private String nome;
    private String descrizione;
    private double costo;
    private String nomeSecondaLingua;
    private String descrizioneSecondaLingua;
    private String allergeni;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "nome", referencedColumnName = "id_singolo_ordine", foreignKey = @ForeignKey(name = "singolo_ordine_fkey"))
    private List<SingoloOrdine> singoloOrdine;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", costo=" + costo +
                ", nomeSecondaLingua='" + nomeSecondaLingua + '\'' +
                ", descrizioneSecondaLingua='" + descrizioneSecondaLingua + '\'' +
                ", allergeni='" + allergeni + '\'' +
                '}';
    }
}
