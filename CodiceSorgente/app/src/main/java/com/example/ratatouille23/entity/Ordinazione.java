package com.example.ratatouille23.entity;

import java.util.Iterator;
import java.util.List;

public class Ordinazione {

    private int id_ordinazione;
    private int numeroTavolo;
    private int numeroCommensali;
    private int idAttivita;
    private List<SingoloOrdine> listaProdotti;

    public Ordinazione(){

    }

    public Ordinazione(List<SingoloOrdine> listaProdotti, int numeroTavolo, int numeroCommensali) {
        this.listaProdotti = listaProdotti;
        this.numeroTavolo = numeroTavolo;
        this.numeroCommensali = numeroCommensali;
    }

    public Ordinazione(int numeroTavolo, int numeroCommensali) {
        this.numeroTavolo = numeroTavolo;
        this.numeroCommensali = numeroCommensali;
    }

    public Ordinazione(List<SingoloOrdine> listaProdotti) {
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

    public int getId_ordinazione() {
        return id_ordinazione;
    }

    public void setId_ordinazione(int id_ordinazione) {
        this.id_ordinazione = id_ordinazione;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    public List<SingoloOrdine> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<SingoloOrdine> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public double calcolaTotale() {
        Iterator<SingoloOrdine> listaProdottiIterator = this.listaProdotti.iterator();
        SingoloOrdine currentSingoloOrdine;
        double totale = 0;

        while(listaProdottiIterator.hasNext()) {
            currentSingoloOrdine = listaProdottiIterator.next();
            totale = totale + currentSingoloOrdine.calcolaTotale();
        }

        return totale;
    }

    @Override
    public String toString() {
        return "Ordinazione{" +
                "id_ordinazione=" + id_ordinazione +
                ", numeroTavolo=" + numeroTavolo +
                ", numeroCommensali=" + numeroCommensali +
                ", idAttivita='" + idAttivita + '\'' +
                ", listaProdotti=" + listaProdotti +
                '}';
    }
}
