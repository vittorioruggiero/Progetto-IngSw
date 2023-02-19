package com.example.ratatouille23;

import java.util.Iterator;
import java.util.List;

public class Ordinazione {

    private List<SingoloOrdine> listaProdotti;
    private int numeroTavolo;
    private int numeroCommensali;

    public Ordinazione(List<SingoloOrdine> listaProdotti, int numeroTavolo, int numeroCommensali) {
        this.listaProdotti = listaProdotti;
        this.numeroTavolo = numeroTavolo;
        this.numeroCommensali = numeroCommensali;
    }

    public Ordinazione(List<SingoloOrdine> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public List<SingoloOrdine> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<SingoloOrdine> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public int getNumeroTavolo() {
        return numeroTavolo;
    }

    public void setNumeroTavolo(int numeroTavolo) {
        this.numeroTavolo = numeroTavolo;
    }

    public int getNumeroCommensali() {
        return numeroCommensali;
    }

    public void setNumeroCommensali(int numeroCommensali) {
        this.numeroCommensali = numeroCommensali;
    }

    public double calcolaTotale() {
        Iterator<SingoloOrdine> listaProdottiIterator = this.listaProdotti.iterator();
        double totale = 0;

        while(listaProdottiIterator.hasNext()) {
            totale = totale + listaProdottiIterator.next().getProdottoMenu().getPrezzo() * listaProdottiIterator.next().getQuantitaProdotto();
        }

        return totale;
    }
}
