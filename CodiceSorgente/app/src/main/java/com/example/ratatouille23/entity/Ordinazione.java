package com.example.ratatouille23.entity;

import java.util.Iterator;
import java.util.List;

public class Ordinazione {

    private int id_ordinazione;
    private int numeroTavolo;
    private int numeroCommensali;
    private int id_conto;
    private String nomeAttivita;
    private String indirizzoAttivita;
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

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    public String getIndirizzoAttivita() {
        return indirizzoAttivita;
    }

    public void setIndirizzoAttivita(String indirizzoAttivita) {
        this.indirizzoAttivita = indirizzoAttivita;
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
                ", id_conto=" + id_conto +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                ", listaProdotti=" + listaProdotti +
                '}';
    }
}
