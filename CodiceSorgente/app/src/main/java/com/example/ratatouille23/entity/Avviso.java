package com.example.ratatouille23.entity;

public class Avviso {

    private int id;
    private String avviso;
    private int idAttivita;
    private String email;

    public Avviso(){

    }

    public Avviso(String avviso) {
        this.avviso = avviso;
    }

    public String getAvviso() {
        return avviso;
    }

    public void setAvviso(String avviso) {
        this.avviso = avviso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Avviso{" +
                "id=" + id +
                ", avviso='" + avviso + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
