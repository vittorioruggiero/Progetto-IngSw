package com.example.ratatouille23.entity;

public class Immagine {

    private int id;
    private String uri;
    private int idAttivita;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    @Override
    public String toString() {
        return "Immagine{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", idAttivita=" + idAttivita +
                '}';
    }
}
