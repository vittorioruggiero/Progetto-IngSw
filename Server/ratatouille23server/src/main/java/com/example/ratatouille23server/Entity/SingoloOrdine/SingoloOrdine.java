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
    private int quantita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordinazione", referencedColumnName = "id_ordinazione", foreignKey = @ForeignKey(name = "ordinazione_fkey"))
    private Ordinazione ordinazioneSingoloOrdine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeProdotto", referencedColumnName = "nomeProdotto", foreignKey = @ForeignKey(name = "prodotto_fkey"))
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

    @Override
    public String toString() {
        return "SingoloOrdine{" +
                "id_singolo_ordine=" + id_singolo_ordine +
                ", quantita=" + quantita +
                '}';
    }
}
