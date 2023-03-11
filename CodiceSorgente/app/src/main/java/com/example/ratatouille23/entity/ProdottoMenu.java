package com.example.ratatouille23.entity;

public class ProdottoMenu{

    private String nomeProdotto;
    private String nomeSecondaLingua;
    private String descrizione;
    private String descrizioneSecondaLingua;
    private double costo;
    private String allergeni;

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
}
