package com.example.ratatouille23.entity;

public class Attivita {

    private String nome;
    private String indirizzo;
    private String telefono;
    private int capienza;

    public Attivita(String nome, String indirizzo, String telefono, int capienza) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.capienza = capienza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
}
