package com.example.ratatouille23.entity;

public class Attivita {

    private int id;
    private String nome;
    private String indirizzo;
    private String telefono;
    private int capienza;

    public Attivita(){

    }

    public Attivita(int id, String nome, String indirizzo, String telefono, int capienza) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.capienza = capienza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Attivita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", capienza=" + capienza +
                '}';
    }
}
