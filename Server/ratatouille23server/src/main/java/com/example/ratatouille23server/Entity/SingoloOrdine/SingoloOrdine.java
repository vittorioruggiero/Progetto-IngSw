package com.example.ratatouille23server.Entity.SingoloOrdine;

import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import jakarta.persistence.*;
@Entity
@Table(name = "singoloordine")
public class SingoloOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_singolo_ordine;
    @Column(nullable = false)
    private int quantita;
    @Column(name = "id_ordinazione")
    private int id_ordinazione;
    @Column(name = "nomeProdotto")
    private String nomeProdotto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordinazione", referencedColumnName = "id_ordinazione",
            foreignKey = @ForeignKey(name = "ordinazione_fkey"), insertable = false, updatable = false)
    private Ordinazione ordinazioneSingoloOrdine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeProdotto", referencedColumnName = "nomeProdotto",
            foreignKey = @ForeignKey(name = "prodotto_fkey"), insertable = false, updatable = false)
    private ProdottoMenu prodottoMenuSingoloOrdine;


    public int getId_singolo_ordine() {
        return id_singolo_ordine;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getId_ordinazione() {
        return id_ordinazione;
    }

    public void setId_ordinazione(int id_ordinazione) {
        this.id_ordinazione = id_ordinazione;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    @Override
    public String toString() {
        return "SingoloOrdine{" +
                "id_singolo_ordine=" + id_singolo_ordine +
                ", quantita=" + quantita +
                ", id_ordinazione=" + id_ordinazione +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                '}';
    }
}
