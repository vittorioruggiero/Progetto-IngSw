package com.example.ratatouille23.entity;

public class ProdottoMenu{

    private String nomeProdotto;
    private String nomeSecondaLingua;
    private String descrizione;
    private String descrizioneSecondaLingua;
    private double costo;
    private String allergeni;
    private String nomeSezione;

    public ProdottoMenu(){

    }

    public ProdottoMenu(String nome, String nomeSecondaLingua, String ingredienti, String ingredientiSecondaLingua, double prezzo) {
        this.nomeProdotto = nome;
        this.nomeSecondaLingua = nomeSecondaLingua;
        this.descrizione = ingredienti;
        this.descrizioneSecondaLingua = ingredientiSecondaLingua;
        this.costo = prezzo;
    }

    public ProdottoMenu(String nome, String ingredienti, double prezzo) {
        this.nomeProdotto = nome;
        this.descrizione = ingredienti;
        this.costo = prezzo;
    }

    public ProdottoMenu(String nome, double prezzo, String nomeSecondaLingua) {
        this.nomeProdotto = nome;
        this.nomeSecondaLingua = nomeSecondaLingua;
        this.costo = prezzo;
    }


    public ProdottoMenu(String nome, double prezzo) {
        this.nomeProdotto = nome;
        this.costo = prezzo;
    }

    public ProdottoMenu(String nomeProdotto, String descrizione, double costo, String allergeni) {
        this.nomeProdotto = nomeProdotto;
        this.descrizione = descrizione;
        this.costo = costo;
        this.allergeni = allergeni;
    }

    public ProdottoMenu(String nomeProdotto, String nomeSecondaLingua, String descrizione, String descrizioneSecondaLingua, double costo, String allergeni) {
        this.nomeProdotto = nomeProdotto;
        this.nomeSecondaLingua = nomeSecondaLingua;
        this.descrizione = descrizione;
        this.descrizioneSecondaLingua = descrizioneSecondaLingua;
        this.costo = costo;
        this.allergeni = allergeni;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getNomeSecondaLingua() {
        return nomeSecondaLingua;
    }

    public void setNomeSecondaLingua(String nomeSecondaLingua) {
        this.nomeSecondaLingua = nomeSecondaLingua;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneSecondaLingua() {
        return descrizioneSecondaLingua;
    }

    public void setDescrizioneSecondaLingua(String descrizioneSecondaLingua) {
        this.descrizioneSecondaLingua = descrizioneSecondaLingua;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getAllergeni() {
        return allergeni;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
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
                "nomeProdotto='" + nomeProdotto + '\'' +
                ", nomeSecondaLingua='" + nomeSecondaLingua + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", descrizioneSecondaLingua='" + descrizioneSecondaLingua + '\'' +
                ", costo=" + costo +
                ", allergeni='" + allergeni + '\'' +
                ", nomeSezione='" + nomeSezione + '\'' +
                '}';
    }
}
