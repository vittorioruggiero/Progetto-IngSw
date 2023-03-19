package com.example.ratatouille23.entity;

public class SingoloOrdine {

    private int id_singolo_ordine;
    private ProdottoMenu prodottoMenu;
    private int quantita;
    private int idOrdinazione;
    private String nomeProdotto;
    public SingoloOrdine(){

    }

    public SingoloOrdine(ProdottoMenu prodottoMenu, int quantita) {
        this.prodottoMenu = prodottoMenu;
        this.quantita = quantita;
    }

    public ProdottoMenu getProdottoMenu() {
        return prodottoMenu;
    }

    public void setProdottoMenu(ProdottoMenu prodottoMenu) {
        this.prodottoMenu = prodottoMenu;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getId_singolo_ordine() {
        return id_singolo_ordine;
    }

    public void setId_singolo_ordine(int id_singolo_ordine) {
        this.id_singolo_ordine = id_singolo_ordine;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getIdOrdinazione() {
        return idOrdinazione;
    }

    public void setIdOrdinazione(int idOrdinazione) {
        this.idOrdinazione = idOrdinazione;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public double calcolaTotale() {
        return this.prodottoMenu.getCosto() * this.quantita;
    }

    @Override
    public String toString() {
        return "SingoloOrdine{" +
                "id_singolo_ordine=" + id_singolo_ordine +
                ", prodottoMenu=" + prodottoMenu +
                ", quantita=" + quantita +
                ", idOrdinazione=" + idOrdinazione +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                '}';
    }
}
