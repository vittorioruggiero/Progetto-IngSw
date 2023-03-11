package com.example.ratatouille23.entity;

import java.io.Serializable;

public class AttivitaPkey implements Serializable {

    private String nome;
    private String indirizzo;

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
}
