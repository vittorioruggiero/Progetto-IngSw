package com.example.ratatouille23;

public class ProdottoMenu{

    private String nome;
    private String nomeSecondaLingua;
    private String ingredienti;
    private String ingredientiSecondaLingua;
    private double prezzo;

    public ProdottoMenu(String nome, String nomeSecondaLingua, String ingredienti, String ingredientiSecondaLingua, double prezzo) {
        this.nome = nome;
        this.nomeSecondaLingua = nomeSecondaLingua;
        this.ingredienti = ingredienti;
        this.ingredientiSecondaLingua = ingredientiSecondaLingua;
        this.prezzo = prezzo;
    }

    public ProdottoMenu(String nome, String ingredienti, double prezzo) {
        this.nome = nome;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSecondaLingua() {
        return nomeSecondaLingua;
    }

    public void setNomeSecondaLingua(String nomeSecondaLingua) {
        this.nomeSecondaLingua = nomeSecondaLingua;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getIngredientiSecondaLingua() {
        return ingredientiSecondaLingua;
    }

    public void setIngredientiSecondaLingua(String ingredientiSecondaLingua) {
        this.ingredientiSecondaLingua = ingredientiSecondaLingua;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
