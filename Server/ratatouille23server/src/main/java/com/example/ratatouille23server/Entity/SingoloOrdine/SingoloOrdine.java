package com.example.ratatouille23server.Entity.SingoloOrdine;

import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "singoloordine")
public class SingoloOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_singolo_ordine;
    @Column(nullable = false)
    private int quantita;
    @Column(name = "id_ordinazione")
    private int idOrdinazione;
    @Column(name = "nomeProdotto")
    private String nomeProdotto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordinazione", referencedColumnName = "id_ordinazione",
            foreignKey = @ForeignKey(name = "ordinazione_fkey"), insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ordinazione ordinazioneSingoloOrdine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeProdotto", referencedColumnName = "nomeProdotto",
            foreignKey = @ForeignKey(name = "prodotto_fkey"), insertable = false, updatable = false)
    private ProdottoMenu prodottoMenuSingoloOrdine;


    public void setId_singolo_ordine(int id_singolo_ordine) {
        this.id_singolo_ordine = id_singolo_ordine;
    }

    public int getId_singolo_ordine() {
        return id_singolo_ordine;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdOrdinazione() {
        return idOrdinazione;
    }

    public void setIdOrdinazione(int id_ordinazione) {
        this.idOrdinazione = id_ordinazione;
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
                ", id_ordinazione=" + idOrdinazione +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                '}';
    }
}
